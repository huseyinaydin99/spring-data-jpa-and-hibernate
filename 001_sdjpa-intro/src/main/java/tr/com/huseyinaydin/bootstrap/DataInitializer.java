package tr.com.huseyinaydin.bootstrap;

import org.springframework.boot.CommandLineRunner;
import tr.com.huseyinaydin.domain.Book;
import tr.com.huseyinaydin.repositories.BookRepository;

public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("İş Güdümlü Tasarım", "1453", "Hüseyin AYDIN");
        System.out.println("Id: " + bookDDD.getId());

        Book savedDDD = bookRepository.save(bookDDD);
        System.out.println("Id: " + savedDDD.getId());

        Book bookSIA = new Book("Spring İle Yol Al","1071","Hüseyin AYDIN");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(b -> {
            System.out.println("Kitap başlığı: " + bookDDD.getTitle());
            System.out.println("Kitap ISBN: " + bookDDD.getIsbn());
            System.out.println("Kitap yazarı: " + bookDDD.getPublisher());
        });
    }
}