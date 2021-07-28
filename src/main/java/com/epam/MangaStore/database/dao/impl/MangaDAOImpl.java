package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
import com.epam.MangaStore.entity.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.ACCESS_STATUS_DELETED_ID;

public class MangaDAOImpl implements MangaDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_MANGA_BY_ID = "SELECT * FROM manga WHERE id = ?";
    private static final String SELECT_ALL_MANGAS = "SELECT * FROM manga";
    private static final String INSERT_MANGA =
            "INSERT INTO manga " +
                    "(title, description, release_date, language_id, publisher_id, cover_id, releasing_status_id, access_status_id) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_MANGA = "UPDATE manga SET title=?,description=?,release_date=?,language_id=?," +
            "publisher_id=?,cover_id=?,releasing_status_id=?,access_status_id=? WHERE id=?";
    private static final String SELECT_ALL_BY_TITLE = "SELECT * FROM manga WHERE title=?";
    private static final String SELECT_DELETED_MANGA_BY_ID =
            "SELECT * FROM manga WHERE id = ? AND access_status_id=" + ACCESS_STATUS_DELETED_ID;
    private static final String SELECT_ALL_BY_FILTER =
            "SELECT * FROM  manga m " +
                    "INNER JOIN manga2genre m2g on m.id = m2g.manga_id " +
                    "INNER JOIN genre g on m2g.genre_id = g.id " +
                    "WHERE g.id = ? AND g.language_id = ?";

    private Manga getMangaByResultSet(ResultSet resultSet) throws SQLException {
        Manga manga = new Manga();
        manga.setId(resultSet.getLong("id"));
        manga.setTitle(resultSet.getString("title"));
        manga.setDescription(resultSet.getString("description"));
        manga.setReleaseDate(resultSet.getDate("release_date"));
        manga.setLanguageID(resultSet.getInt("language_id"));
        manga.setPublisherID(resultSet.getLong("publisher_id"));
        manga.setReleaseStatusID(resultSet.getInt("releasing_status_id"));
        manga.setAccessStatusID(resultSet.getInt("access_status_id"));
        manga.setCoverID(resultSet.getLong("cover_id"));
        return manga;
    }

    @Override
    public Long insert(Manga manga) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MANGA, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manga.getTitle());
            preparedStatement.setString(2, manga.getDescription());
            preparedStatement.setDate(3, manga.getReleaseDate());
            preparedStatement.setInt(4, manga.getLanguageID());
            preparedStatement.setLong(5, manga.getPublisherID());
            preparedStatement.setLong(6, manga.getCoverID());
            preparedStatement.setInt(7, manga.getReleaseStatusID());
            preparedStatement.setInt(8, manga.getAccessStatusID());
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
    public void update(Manga manga) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MANGA)) {
            preparedStatement.setString(1, manga.getTitle());
            preparedStatement.setString(2, manga.getDescription());
            preparedStatement.setDate(3, manga.getReleaseDate());
            preparedStatement.setInt(4, manga.getLanguageID());
            preparedStatement.setLong(5, manga.getPublisherID());
            preparedStatement.setLong(6, manga.getCoverID());
            preparedStatement.setInt(7, manga.getReleaseStatusID());
            preparedStatement.setInt(8, manga.getAccessStatusID());
            preparedStatement.setLong(9, manga.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Manga selectByID(Long mangaID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Manga manga = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MANGA_BY_ID)) {
            preparedStatement.setLong(1, mangaID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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

    @Override
    public List<Manga> selectByTitle(String title) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Manga> mangas = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_TITLE)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mangas.add(getMangaByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return mangas;
    }

    public List<Manga> selectAllByFilter(Integer genreID, Integer localID) throws SQLException {
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

    @Override
    public boolean isMangaDeleted(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isDeletedMangaExist;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELETED_MANGA_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            isDeletedMangaExist = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isDeletedMangaExist;
    }
}

