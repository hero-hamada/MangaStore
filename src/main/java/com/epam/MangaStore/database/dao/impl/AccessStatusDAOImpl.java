package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.AccessStatusDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessStatusDAOImpl implements AccessStatusDAO {

    private ConnectionPool connectionPool;
    private Connection connection;
    private static final String SELECT_VOLUME_BY_ID = "SELECT * FROM access_status WHERE id=?";

    @Override
    public boolean isAccessStatusExists(Integer statusID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExists;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOLUME_BY_ID)) {
            preparedStatement.setLong(1, statusID);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExists = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExists;
    }
}
