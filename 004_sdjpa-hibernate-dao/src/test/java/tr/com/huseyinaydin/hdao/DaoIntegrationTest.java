package tr.com.huseyinaydin.hdao;

import tr.com.huseyinaydin.hdao.dao.AuthorDao;
import tr.com.huseyinaydin.hdao.dao.AuthorDaoImpl;
import tr.com.huseyinaydin.hdao.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;

//    @Autowired
//    BookDao bookDao;
//
//    @Test
//    void testDeleteBook() {
//        Book book = new Book();
//        book.setIsbn("1234");
//        book.setPublisher("Hüseyin");
//        book.setTitle("Kitap 1");
//        Book saved = bookDao.saveNewBook(book);
//
//        bookDao.deleteBookById(saved.getId());
//
//        Book deleted = bookDao.getById(saved.getId());
//
//        assertThat(deleted).isNull();
//    }
//
//    @Test
//    void updateBookTest() {
//        Book book = new Book();
//        book.setIsbn("1234");
//        book.setPublisher("Hüseyin");
//        book.setTitle("Kitap 1");
//
//        Author author = new Author();
//        author.setId(3L);
//
//        book.setAuthor(author);
//        Book saved = bookDao.saveNewBook(book);
//
//        saved.setTitle("Yep Yeni Kitap");
//        bookDao.updateBook(saved);
//
//        Book fetched = bookDao.getById(saved.getId());
//
//        assertThat(fetched.getTitle()).isEqualTo("Yep Yeni Kitap");
//    }
//
//    @Test
//    void testSaveBook() {
//        Book book = new Book();
//        book.setIsbn("1234");
//        book.setPublisher("Hüseyin");
//        book.setTitle("Yep Yeni Kitap");
//
//        Author author = new Author();
//        author.setId(3L);
//
//        book.setAuthor(author);
//        Book saved = bookDao.saveNewBook(book);
//
//        assertThat(saved).isNotNull();
//    }
//
//    @Test
//    void testGetBookByName() {
//        Book book = bookDao.findBookByTitle("Tertemiz Kodla");
//
//        assertThat(book).isNotNull();
//    }
//
//    @Test
//    void testGetBook() {
//        Book book = bookDao.getById(3L);
//
//        assertThat(book.getId()).isNotNull();
//    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("hüseyin");
        author.setLastName("aydın");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            Author deleted = authorDao.getById(saved.getId());
        });

    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("hüseyin");
        author.setLastName("aydın");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Lion");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Lion");
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("Hüseyin");
        author.setLastName("AYDIN");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Hüseyin", "AYDIN");

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthor() {
        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();
    }
}