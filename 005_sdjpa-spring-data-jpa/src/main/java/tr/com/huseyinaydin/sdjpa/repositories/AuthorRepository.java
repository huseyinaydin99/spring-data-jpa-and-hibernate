package tr.com.huseyinaydin.sdjpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.huseyinaydin.sdjpa.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
    Page<Author> findAuthorByLastName(String lastName, Pageable pageable);
}