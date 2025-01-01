package tr.com.huseyinaydin.jdbc.dao;

import tr.com.huseyinaydin.jdbc.domain.Author;

public interface AuthorDao {
    Author getById(Long id);
}