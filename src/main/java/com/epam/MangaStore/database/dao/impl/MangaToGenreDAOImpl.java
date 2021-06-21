package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.MangaToGenreDAO;

import java.sql.*;

public class MangaToGenreDAOImpl implements MangaToGenreDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String INSERT = "INSERT INTO manga2genre (manga_id, genre_id) VALUES (?,?)";
    private static final String SELECT_BY_MANGA_GENRE_ID = "SELECT * FROM manga2genre WHERE manga_id=? AND genre_id=?";
    private static final String DELETE_BY_MANGA_GENRE_ID = "DELETE FROM manga2genre WHERE manga_id = ? AND genre_id=?";

    @Override
    public Long insert(Long mangaID, Integer genreID) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setLong(2, genreID);
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
    public boolean isPairExists(Long mangaID, Integer genreID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExist;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_MANGA_GENRE_ID)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setLong(2, genreID);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExist;
    }

    @Override
    public void delete(Long mangaID, Integer genreID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_MANGA_GENRE_ID)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setLong(2, genreID);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
