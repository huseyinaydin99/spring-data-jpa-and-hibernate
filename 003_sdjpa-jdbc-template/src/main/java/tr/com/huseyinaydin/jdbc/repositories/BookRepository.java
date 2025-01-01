package tr.com.huseyinaydin.jdbc.repositories;

import tr.com.huseyinaydin.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}