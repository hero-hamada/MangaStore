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

    private static final String SELECT_GENRES_BY_MANGA_LANGUAGE_ID =
                "SELECT * FROM manga m " +
                "INNER JOIN manga2genre m2g on m.id = m2g.manga_id " +
                "INNER JOIN genre g on m2g.genre_id = g.id " +
                "WHERE m.id = ? AND  g.language_id = ?";

    private static final String SELECT_ALL = "SELECT * FROM genre WHERE language_id = ?";

    public Genre getGenreByResultSet(ResultSet resultSet) throws SQLException{
        Long id = resultSet.getLong("id");
        Integer languageID = resultSet.getInt("language_id");
        String name = resultSet.getString("name");

        Genre genre = new Genre();
        genre.setId(id);
        genre.setLanguageID(languageID);
        genre.setName(name);

        return genre;
    }


    public List<Genre> selectGenresByMangaLanguageID(Long mangaID, Integer sessionLanguageID)  throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRES_BY_MANGA_LANGUAGE_ID)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setInt(2, sessionLanguageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                genres.add(getGenreByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return genres;
    }
    public List<Genre> selectAll(Integer sessionLanguageID)  throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, sessionLanguageID);
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
