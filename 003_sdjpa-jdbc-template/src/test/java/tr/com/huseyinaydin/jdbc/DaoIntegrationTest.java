package tr.com.huseyinaydin.jdbc;

import tr.com.huseyinaydin.jdbc.dao.AuthorDao;

import tr.com.huseyinaydin.jdbc.dao.AuthorDaoImpl;
import tr.com.huseyinaydin.jdbc.domain.Author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthorDaoImpl.class)
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("hüseyin");
        author.setLastName("aydin");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        Author deleted = authorDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("hüseyin");
        author.setLastName("aydın");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Backhum");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Backhum");
    }

    @Test
    void testInsertAuthor() {
        Author author = new Author();
        author.setFirstName("huseyin");
        author.setLastName("aydın");

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
        Author author = authorDao.getById(1l);

        assertThat(author.getId()).isNotNull();
    }
}