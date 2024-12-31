package tr.com.huseyinaydin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.domain.composite.AuthorComposite;
import tr.com.huseyinaydin.domain.composite.NameId;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}