package tr.com.huseyinaydin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tr.com.huseyinaydin.domain.Book;
import tr.com.huseyinaydin.repositories.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpringBootJpaTestSlice {
    @Autowired
    BookRepository bookRepository;

    @Test
    void testJpaTestSplice(){
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("Kitap 1", "1994", "Hüseyin AYDIN"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }
}