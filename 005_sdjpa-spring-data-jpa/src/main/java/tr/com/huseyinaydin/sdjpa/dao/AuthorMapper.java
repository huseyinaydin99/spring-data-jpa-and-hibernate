package tr.com.huseyinaydin.sdjpa.dao;

import org.springframework.jdbc.core.RowMapper;
import tr.com.huseyinaydin.sdjpa.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));

        return author;
    }
}