package tr.com.huseyinaydin.sdjpa.dao;

import tr.com.huseyinaydin.sdjpa.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}