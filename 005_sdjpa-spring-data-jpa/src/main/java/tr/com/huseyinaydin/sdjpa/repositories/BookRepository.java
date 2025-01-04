package tr.com.huseyinaydin.sdjpa.repositories;

import org.springframework.lang.Nullable;
import tr.com.huseyinaydin.sdjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);
}