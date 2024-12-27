package tr.com.huseyinaydin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}