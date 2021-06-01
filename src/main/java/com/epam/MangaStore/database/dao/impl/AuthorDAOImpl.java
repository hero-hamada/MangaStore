package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.AuthorDAO;
import com.epam.MangaStore.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    private static final String SELECT_ALL_AUTHORS_BY_MANGA_ID =
            "SELECT a.id, a.first_name, a.middle_name, a.last_name FROM manga m " +
                    "INNER JOIN manga2author m2m ON m.id = m2m.manga_id " +
                    "INNER JOIN author a ON m2m.author_id = a.id " +
                    "WHERE m.id = ?;";

    public Author getAuthorByResultSet(ResultSet resultSet) throws SQLException{

        Author author = new Author();

        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        author.setLastName(resultSet.getString("last_name"));

        return author;
    }


    private ConnectionPool connectionPool;
    private Connection connection;

    public List<Author> selectAllAuthorsByMangaID(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Author> authors = new ArrayList<>();
        Author author;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTHORS_BY_MANGA_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author = getAuthorByResultSet(resultSet);
                authors.add(author);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return authors;
    }

}
