package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.MangaToAuthorDAO;

import java.sql.*;

public class MangaToAuthorDAOImpl implements MangaToAuthorDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String INSERT = "INSERT INTO manga2author (manga_id, author_id) VALUES (?,?)";
    private static final String SELECT_BY_MANGA_AUTHOR_ID = "SELECT * FROM manga2author WHERE manga_id=? AND author_id=?";
    private static final String DELETE_BY_MANGA_AUTHOR_ID = "DELETE FROM manga2author WHERE manga_id = ? AND author_id=?";

    @Override
    public Long insert(Long mangaID, Long authorID) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setLong(2, authorID);
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
    public void delete(Long mangaID, Long authorID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_MANGA_AUTHOR_ID)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setLong(2, authorID);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean isPairExists(Long mangaID, Long authorID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExist;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_MANGA_AUTHOR_ID)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setLong(2, authorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExist;
    }
}
