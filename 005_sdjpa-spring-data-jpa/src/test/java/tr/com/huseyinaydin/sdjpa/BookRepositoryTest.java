package tr.com.huseyinaydin.sdjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import tr.com.huseyinaydin.sdjpa.domain.Book;
import tr.com.huseyinaydin.sdjpa.repositories.BookRepository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"tr.com.huseyinaydin.sdjpa"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testBookQueryNative() {
        Book book = bookRepository.findBookByTitleNativeQuery("Ter Temiz Kodlar Gıcır");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookJPANamedQuery() {
        Book book = bookRepository.jpaNamed("Ter Temiz Kodlar Gıcır");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookQueryNamed() {
        Book book = bookRepository.findBookByTitleWithQueryNamed("Ter Temiz Kodlar Gıcır");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookQuery() {
        Book book = bookRepository.findBookByTitleWithQuery("Ter Temiz Kodlar Gıcır");

        assertThat(book).isNotNull();
    }

    @Test
    void testBookFuture() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepository.queryByTitle("Ter Temiz Kodlar Gıcır");

        Book book = bookFuture.get();

        assertNotNull(book);
    }

    @Test
    void testBookStream() {
        AtomicInteger count = new AtomicInteger();

        bookRepository.findAllByTitleNotNull().forEach(book -> {
            count.incrementAndGet();
        });

        assertThat(count.get()).isGreaterThan(5);
    }

    @Test
    void testEmptyResultException() {

        assertThrows(EmptyResultDataAccessException.class, () -> {
            Book book = bookRepository.readByTitle("baslik1");
        });
    }

    @Test
    void testNullParam() {
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    void testNoException() {

        assertNull(bookRepository.getByTitle("baslik"));
    }
}