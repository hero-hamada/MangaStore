package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.AuthorDAO;
import com.epam.MangaStore.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String INSERT_AUTHOR = "INSERT INTO author (first_name, middle_name, last_name) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM author";
    private static final String UPDATE_AUTHOR = "UPDATE author SET first_name=?, middle_name=?, last_name=? WHERE id=?";
    private static final String SELECT_AUTHOR_BY_ID = "SELECT * FROM author WHERE id=?";
    private static final String SELECT_AUTHOR_BY_FULL_NAME = "SELECT * FROM author WHERE first_name=? AND middle_name=? AND last_name=?";
    private static final String SELECT_ALL_AUTHORS_BY_MANGA_ID =
            "SELECT a.id, a.first_name, a.middle_name, a.last_name FROM manga m\n" +
                    "INNER JOIN manga2author m2m ON m.id = m2m.manga_id\n" +
                    "INNER JOIN author a ON m2m.author_id = a.id \n" +
                    "WHERE m.id = ?";

    private Author getAuthorByResultSet(ResultSet resultSet) throws SQLException{

        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        author.setLastName(resultSet.getString("last_name"));
        return author;
    }

    @Override
    public Long insert(Author author) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getMiddleName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getLong(1);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return generatedID;
    }

    @Override
    public void update(Author author) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getMiddleName());
            preparedStatement.setString(3, author.getLastName());
            preparedStatement.setLong(4, author.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Author selectByID(Long authorID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Author author = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_BY_ID)) {
            preparedStatement.setLong(1, authorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author = getAuthorByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return author;
    }

    public List<Author> selectAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Author> authors = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                authors.add(getAuthorByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return authors;
    }

    @Override
    public Long selectIdByFullName(Author author) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_BY_FULL_NAME)) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getMiddleName());
            preparedStatement.setString(3, author.getLastName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return id;
    }

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
