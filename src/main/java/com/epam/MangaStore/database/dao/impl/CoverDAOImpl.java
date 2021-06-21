package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.CoverDAO;

import java.io.InputStream;
import java.sql.*;

public class CoverDAOImpl implements CoverDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_COVER_BY_ID = "SELECT cover FROM cover WHERE id = ?";
    private static final String INSERT_COVER = "INSERT INTO cover (cover) VALUES (?)";

    @Override
    public Long insert(InputStream uploadingCover) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COVER, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setBlob(1, uploadingCover);
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
