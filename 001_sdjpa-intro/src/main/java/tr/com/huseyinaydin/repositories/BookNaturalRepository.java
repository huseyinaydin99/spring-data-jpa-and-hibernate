package tr.com.huseyinaydin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.domain.BookNatural;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String> {
}