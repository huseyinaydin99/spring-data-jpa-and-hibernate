package tr.com.huseyinaydin.hdao.dao;

import tr.com.huseyinaydin.hdao.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    Author findAuthorByNameCriteria(String huseyin, String aydin);

    List<Author> findAll();

    List<Author> listAuthorByLastNameLike(String lastName);

    Author findAuthorByNameNative(String firstName, String lastName);
}