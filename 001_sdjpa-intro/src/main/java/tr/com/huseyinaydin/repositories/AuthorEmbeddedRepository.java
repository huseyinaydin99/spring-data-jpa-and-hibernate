package tr.com.huseyinaydin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.domain.composite.AuthorEmbedded;
import tr.com.huseyinaydin.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}