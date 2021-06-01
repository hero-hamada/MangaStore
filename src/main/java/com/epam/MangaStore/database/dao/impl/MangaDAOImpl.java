package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
import com.epam.MangaStore.entity.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MangaDAOImpl implements MangaDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_MANGA_BY_ID = "SELECT * FROM manga WHERE id = ?";
    private static final String SELECT_ALL_BY_FILTER =
            "SELECT * FROM manga m\n" +
                    "INNER JOIN manga2genre m2g on m.id = m2g.manga_id\n" +
                    "INNER JOIN genre g on m2g.genre_id = g.id\n" +
                    "WHERE g.id = ? AND g.language_id = ?";

    private static final String SELECT_ALL_MANGAS = "SELECT * FROM manga WHERE is_active = 1 ORDER BY title";


    public Manga getMangaByResultSet(ResultSet resultSet) throws SQLException {

        Manga manga = new Manga();
        manga.setId(resultSet.getLong("id"));
        manga.setTitle(resultSet.getString("title"));
        manga.setDescription(resultSet.getString("description"));
        manga.setReleaseDate(resultSet.getDate("release_date"));
        manga.setLanguageID(resultSet.getInt("language_id"));
        manga.setPublisherID(resultSet.getLong("publisher_id"));
        manga.setStatusID(resultSet.getInt("status_id"));
        manga.setIsActive(resultSet.getBoolean("is_active"));
        manga.setCoverID(resultSet.getLong("cover_id"));

        return manga;
    }

    public Manga selectMangaByID(Long mangaID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Manga manga = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MANGA_BY_ID)) {
            preparedStatement.setLong(1, mangaID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                manga = getMangaByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return manga;
    }

    public List<Manga> selectAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Manga> mangas = new ArrayList<>();
        Manga manga;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MANGAS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                manga = getMangaByResultSet(resultSet);
                mangas.add(manga);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return mangas;
    }

    public List<Manga> selectMangaByFilter(Long genreID, Integer localID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Manga> mangas = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_FILTER)) {
            preparedStatement.setLong(1, genreID);
            preparedStatement.setLong(2, localID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mangas.add(getMangaByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return mangas;
    }

}

