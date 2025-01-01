package tr.com.huseyinaydin.jdbc.repositories;

import tr.com.huseyinaydin.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}