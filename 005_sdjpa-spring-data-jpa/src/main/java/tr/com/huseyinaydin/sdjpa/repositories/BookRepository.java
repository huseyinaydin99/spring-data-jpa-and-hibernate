package tr.com.huseyinaydin.sdjpa.repositories;

import tr.com.huseyinaydin.sdjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}