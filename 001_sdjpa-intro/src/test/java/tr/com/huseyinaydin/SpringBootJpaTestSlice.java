package tr.com.huseyinaydin;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import tr.com.huseyinaydin.domain.Book;
import tr.com.huseyinaydin.repositories.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = "tr.com.huseyinaydin.bootstrap")
public class SpringBootJpaTestSlice {
    @Autowired
    BookRepository bookRepository;

    @Commit //test başarılı olsun olmasın commit edilir.
    @Order(1)
    @Test
    void testJpaTestSplice(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(0);

        bookRepository.save(new Book("Kitap 1", "1994", "Hüseyin AYDIN", null));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Rollback(value = false)//test başarısız olduğunda rollback yapılır.
    @Order(2)
    @Test
    void testJpaTestSpliceTransaction(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(1);
    }
}