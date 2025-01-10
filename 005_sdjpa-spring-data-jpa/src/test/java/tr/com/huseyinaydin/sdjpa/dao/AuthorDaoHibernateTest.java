package tr.com.huseyinaydin.sdjpa.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManagerFactory;
import tr.com.huseyinaydin.sdjpa.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"tr.com.huseyinaydin.sdjpa.dao"})
class AuthorDaoHibernateTest {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoHibernate(entityManagerFactory);
    }

    @Test
    void findAllAuthorsByLastName() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("AYDIN", PageRequest.of(0, 10));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
    }

    @Test
    void findAllAuthorsByLastNameSortLastNameDesc() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("AYDIN",
                PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstname"))));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Yıldırım");
    }

    @Test
    void findAllAuthorsByLastNameSortLastNameAsc() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("AYDIN",
                PageRequest.of(0, 10, Sort.by(Sort.Order.asc("firstname"))));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Yılmaz");
    }

    @Test
    void findAllAuthorsByLastNameAllRecs() {
        List<Author> authors = authorDao.findAllAuthorsByLastName("AYDIN", PageRequest.of(0, 100));

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(40);
    }
}