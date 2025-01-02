package tr.com.huseyinaydin.hdao.repositories;

import tr.com.huseyinaydin.hdao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}