package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.LanguageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageDAOImpl implements LanguageDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ID_BY_NAME = "SELECT id FROM language WHERE name = ?";

    public Integer selectIdByName(String name) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Integer id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return id;
    }

}
