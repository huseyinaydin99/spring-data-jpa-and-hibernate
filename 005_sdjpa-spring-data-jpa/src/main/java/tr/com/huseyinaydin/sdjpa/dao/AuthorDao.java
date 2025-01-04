package tr.com.huseyinaydin.sdjpa.dao;

import tr.com.huseyinaydin.sdjpa.domain.Author;

public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}