package tr.com.huseyinaydin.sdjpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import tr.com.huseyinaydin.sdjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE title = :title", nativeQuery = true)
    Book findBookByTitleNativeQuery(@Param("title") String title);

    @Query("SELECT b FROM Book b where b.title = :title")
    Book findBookByTitleWithQueryNamed(@Param("title") String title);

    @Query("SELECT b FROM Book b where b.title = ?1")
    Book findBookByTitleWithQuery(String title);

    Optional<Book> findBookByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);
}