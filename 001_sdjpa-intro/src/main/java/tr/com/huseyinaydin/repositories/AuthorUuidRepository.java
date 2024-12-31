package tr.com.huseyinaydin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.domain.AuthorUUID;

import java.util.UUID;

public interface AuthorUuidRepository extends JpaRepository<AuthorUUID, UUID> {

}