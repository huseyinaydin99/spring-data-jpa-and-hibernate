package tr.com.huseyinaydin.hdao;

import net.bytebuddy.utility.RandomString;
import tr.com.huseyinaydin.hdao.dao.AuthorDao;
import tr.com.huseyinaydin.hdao.dao.AuthorDaoImpl;
import tr.com.huseyinaydin.hdao.dao.BookDao;
import tr.com.huseyinaydin.hdao.dao.BookDaoImpl;
import tr.com.huseyinaydin.hdao.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import tr.com.huseyinaydin.hdao.domain.Book;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local") // Bu profil test sırasında kullanılacak olan konfigürasyonu belirtir.
@DataJpaTest // Veritabanı ile ilgili test yapılacak sınıf olduğunu belirtir ve testte JPA'yı aktif hale getirir.
@Import({AuthorDaoImpl.class, BookDaoImpl.class}) // Testte kullanmak için AuthorDaoImpl ve BookDaoImpl sınıflarını dahil eder.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Testte gerçek veritabanı bağlantısını korur, otomatik olarak test veritabanı kurulmaz.
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao; // AuthorDao bean'ini test sınıfına enjekte eder.

    @Autowired
    BookDao bookDao; // BookDao bean'ini test sınıfına enjekte eder.

    // Yazarları tümüyle listeleme testi
    @Test
    void testFindAllAuthors() {
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull(); // Sonuç null olmamalıdır.
        assertThat(authors.size()).isGreaterThan(0); // Yazar listesi en az bir yazar içermelidir.
    }

    // ISBN ile kitap arama testi
    @Test
    void testFindBookByISBN() {
        Book book = new Book();
        book.setIsbn("1234" + RandomString.make()); // ISBN'yi rastgele oluşturur.
        book.setTitle("ISBN TEST");

        Book saved = bookDao.saveNewBook(book); // Kitap veritabanına kaydedilir.

        Book fetched = bookDao.findByISBN(book.getIsbn()); // ISBN ile kitap alınır.
        assertThat(fetched).isNotNull(); // Kitap null olmamalıdır.
    }

    // Soyadıyla yazarları listeleme testi
    @Test
    void testListAuthorByLastNameLike() {
        List<Author> authors = authorDao.listAuthorByLastNameLike("Hüseyin");

        assertThat(authors).isNotNull(); // Yazar listesi null olmamalıdır.
        assertThat(authors.size()).isGreaterThan(0); // En az bir yazar olmalıdır.
    }

    // Kitap silme testi
    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Hüseyin");
        book.setTitle("Yep Yeni Kitaplar");
        Book saved = bookDao.saveNewBook(book); // Kitap kaydedilir.

        bookDao.deleteBookById(saved.getId()); // Kitap silinir.

        Book deleted = bookDao.getById(saved.getId()); // Silinen kitap alınmaya çalışılır.

        assertThat(deleted).isNull(); // Silinen kitap null olmalıdır.
    }

    // Kitap güncelleme testi
    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Hüseyin");
        book.setTitle("Yep Yeni Kitap");

        Author author = new Author();
        author.setId(3L);

        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book); // Kitap kaydedilir.

        saved.setTitle("Yep Yeni Kitap");
        bookDao.updateBook(saved); // Kitap güncellenir.

        Book fetched = bookDao.getById(saved.getId()); // Güncellenmiş kitap alınır.

        assertThat(fetched.getTitle()).isEqualTo("Yep Yeni Kitap"); // Kitabın başlığı doğru olmalıdır.
    }

    // Yeni kitap kaydetme testi
    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("hüseyin");
        book.setTitle("Spring ile Yol Al");

        Author author = new Author();
        author.setId(3L);

        book.setAuthorId(1L);
        Book saved = bookDao.saveNewBook(book); // Kitap kaydedilir.

        assertThat(saved).isNotNull(); // Kitap kaydedildikten sonra null olmamalıdır.
    }

    // Kitap başlığıyla arama testi
    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Ter temiz kodlar mis");

        assertThat(book).isNotNull(); // Kitap null olmamalıdır.
    }

    // Kitap başlığıyla Criteria API kullanarak arama testi
    @Test
    void testGetBookByTitleCriteria() {
        Book book = bookDao.findBookByTitleCriteria("Ter temiz kodlar mis");

        assertThat(book).isNotNull(); // Kitap null olmamalıdır.
    }

    // Kitap ID ile alma testi
    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull(); // Kitap ID'si null olmamalıdır.
    }

    // Yazar silme testi
    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("hüseyin");
        author.setLastName("aydın");

        Author saved = authorDao.saveNewAuthor(author); // Yazar kaydedilir.

        authorDao.deleteAuthorById(saved.getId()); // Yazar silinir.

        Author deleted = authorDao.getById(saved.getId()); // Silinen yazar alınmaya çalışılır.
        assertThat(deleted).isNull(); // Silinen yazar null olmalıdır.
    }

    // Yazar güncelleme testi
    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("hüseyin");
        author.setLastName("aydın");

        Author saved = authorDao.saveNewAuthor(author); // Yazar kaydedilir.

        saved.setLastName("Fireharman"); // Yazar soyadı güncellenir.
        Author updated = authorDao.updateAuthor(saved); // Yazar güncellenir.

        assertThat(updated.getLastName()).isEqualTo("Fireharman"); // Soyadı doğru olmalıdır.
    }

    // Yazar kaydetme testi
    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("Hüseyin");
        author.setLastName("AYDIN");
        Author saved = authorDao.saveNewAuthor(author); // Yazar kaydedilir.

        assertThat(saved).isNotNull(); // Yazar null olmamalıdır.
        assertThat(saved.getId()).isNotNull(); // Yazarın ID'si null olmamalıdır.
    }

    // Kitapları listeleme testi
    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAll();

        assertThat(books).isNotNull(); // Kitap listesi null olmamalıdır.
        assertThat(books.size()).isGreaterThan(0); // En az bir kitap olmalıdır.
    }

    // Başlığa göre kitap arama testi
    @Test
    void testFindBookByTitle() {
        Book book = new Book();
        book.setIsbn("1235" + RandomString.make());
        book.setTitle("Baslik Test");

        Book saved = bookDao.saveNewBook(book); // Kitap kaydedilir.

        Book fetched = bookDao.findBookByTitle(book.getTitle()); // Başlıkla kitap aranır.
        assertThat(fetched).isNotNull(); // Kitap null olmamalıdır.

        bookDao.deleteBookById(saved.getId()); // Kitap silinir.
    }

    // Criteria API ile yazar adıyla arama testi
    @Test
    void testGetAuthorByNameCriteria() {
        Author author = authorDao.findAuthorByNameCriteria("Hüseyin", "AYDIN");

        assertThat(author).isNotNull(); // Yazar null olmamalıdır.
    }

    // Yazar adıyla arama testi
    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Hüseyin", "AYDIN");

        assertThat(author).isNotNull(); // Yazar null olmamalıdır.
    }

    // Yazar ID ile alma testi
    @Test
    void testGetAuthor() {
        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull(); // Yazar null olmamalıdır.
    }
}