package tr.com.huseyinaydin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tr.com.huseyinaydin.domain.AuthorUUID;
import tr.com.huseyinaydin.domain.BookNatural;
import tr.com.huseyinaydin.domain.BookUUID;
import tr.com.huseyinaydin.domain.composite.AuthorComposite;
import tr.com.huseyinaydin.domain.composite.NameId;
import tr.com.huseyinaydin.repositories.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "tr.com.huseyinaydin.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Test
    void authorCompositeTest() {
        NameId nameId = new NameId("Hüseyin", "AYDIN");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("TR");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testBookUuid() {
        BookUUID bookUuid = bookUuidRepository.save(new BookUUID());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId());

        BookUUID fetched = bookUuidRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUuid() {
        AuthorUUID authorUuid = authorUuidRepository.save(new AuthorUUID());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUUID fetched = authorUuidRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }
}