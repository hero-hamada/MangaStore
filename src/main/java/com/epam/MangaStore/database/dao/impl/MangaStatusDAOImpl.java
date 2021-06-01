package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.MangaStatusDAO;
import com.epam.MangaStore.entity.MangaStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MangaStatusDAOImpl implements MangaStatusDAO {

    private static final String SELECT_MANGA_STATUS_BY_ID =
            "SELECT * FROM manga_status " +
            "WHERE id = ? AND language_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM manga_status WHERE language_id = ?";

    public MangaStatus getMangaStatusByResultSet(ResultSet resultSet) throws SQLException {

        MangaStatus mangaStatus = new MangaStatus();
        mangaStatus.setId(resultSet.getLong("id"));
        mangaStatus.setLanguageID(resultSet.getInt("language_id"));
        mangaStatus.setName(resultSet.getString("name"));

        return mangaStatus;
    }

    private ConnectionPool connectionPool;
    private Connection connection;

    public MangaStatus selectMangaStatusByID(Integer statusID, Integer sessionLanguageID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        MangaStatus mangaStatus = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MANGA_STATUS_BY_ID)) {
            preparedStatement.setLong(1, statusID);
            preparedStatement.setInt(2, sessionLanguageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mangaStatus = getMangaStatusByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return mangaStatus;
    }
    public List<MangaStatus> selectAll(Integer sessionLanguageID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<MangaStatus> mangaStatuses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, sessionLanguageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mangaStatuses.add(getMangaStatusByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return mangaStatuses;
    }
}
