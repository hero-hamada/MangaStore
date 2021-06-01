package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.CoverDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoverDAOImpl implements CoverDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_COVER_BY_ID = "SELECT cover FROM cover WHERE id = ?";

    public byte[] selectCoverByID(Long id) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        byte[] cover = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COVER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cover = resultSet.getBytes("cover");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return cover;
    }

}
