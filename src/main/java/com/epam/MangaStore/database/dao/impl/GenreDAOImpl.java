package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL = "SELECT * FROM genre WHERE language_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM genre WHERE id = ?";
    private static final String SELECT_GENRES_BY_MANGA_LANGUAGE_ID =
            "SELECT g.id, g.language_id, g.name FROM manga m\n" +
                    "INNER JOIN manga2genre m2g on m.id = m2g.manga_id\n" +
                    "INNER JOIN genre g on m2g.genre_id = g.id\n" +
                    "WHERE m.id = ? AND g.language_id = ?";

    private Genre getGenreByResultSet(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("id"));
        genre.setLanguageID(resultSet.getInt("language_id"));
        genre.setName(resultSet.getString("name"));
        return genre;
    }

    @Override
    public Genre selectByID(Integer genreID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Genre genre = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, genreID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genre = getGenreByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return genre;
    }

    public List<Genre> selectAll(Integer localeID)  throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, localeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(getGenreByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return genres;
    }

    public List<Genre> selectGenresByMangaLanguageID(Long mangaID, Integer localeID)  throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRES_BY_MANGA_LANGUAGE_ID)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setInt(2, localeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(getGenreByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return genres;
    }
}
