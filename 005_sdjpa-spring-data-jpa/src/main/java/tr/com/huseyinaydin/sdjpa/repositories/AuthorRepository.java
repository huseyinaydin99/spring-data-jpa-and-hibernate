package tr.com.huseyinaydin.sdjpa.repositories;

import tr.com.huseyinaydin.sdjpa.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}