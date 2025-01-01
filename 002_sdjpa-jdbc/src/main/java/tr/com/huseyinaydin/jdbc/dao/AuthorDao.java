package tr.com.huseyinaydin.jdbc.dao;

import tr.com.huseyinaydin.jdbc.domain.Author;

public interface AuthorDao {
    Author getById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    Author saveNewAuthor(Author author);
    Author updateAuthor(Author saved);
}