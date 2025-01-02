package tr.com.huseyinaydin.hdao.repositories;

import tr.com.huseyinaydin.hdao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}