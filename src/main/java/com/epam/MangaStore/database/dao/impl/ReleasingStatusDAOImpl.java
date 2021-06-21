package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.ReleasingStatusDAO;
import com.epam.MangaStore.entity.ReleasingStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReleasingStatusDAOImpl implements ReleasingStatusDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_BY_ID =
            "SELECT * FROM releasing_status " +
            "WHERE id = ? AND language_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM releasing_status WHERE language_id = ?";

    private ReleasingStatus getByResultSet(ResultSet resultSet) throws SQLException {
        ReleasingStatus releasingStatus = new ReleasingStatus();
        releasingStatus.setId(resultSet.getLong("id"));
        releasingStatus.setLanguageID(resultSet.getInt("language_id"));
        releasingStatus.setName(resultSet.getString("name"));
        return releasingStatus;
    }

    public ReleasingStatus selectByID(Integer statusID, Integer sessionLanguageID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        ReleasingStatus releasingStatus = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, statusID);
            preparedStatement.setInt(2, sessionLanguageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                releasingStatus = getByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return releasingStatus;
    }

    public List<ReleasingStatus> selectAll(Integer sessionLanguageID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<ReleasingStatus> releasingStatuses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, sessionLanguageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                releasingStatuses.add(getByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return releasingStatuses;
    }
}
