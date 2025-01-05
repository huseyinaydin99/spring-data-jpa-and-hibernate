package tr.com.huseyinaydin.sdjpa.dao;

import tr.com.huseyinaydin.sdjpa.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    List<Book> findAllBooks();

    List<Book> findAllBooks(int pageSize, int offset);
}