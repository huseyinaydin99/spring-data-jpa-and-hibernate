package tr.com.huseyinaydin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.huseyinaydin.domain.BookUUID;

import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUUID, UUID> {
}