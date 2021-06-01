package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.PublisherDAO;
import com.epam.MangaStore.entity.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PublisherDAOImpl implements PublisherDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_PUBLISHER_BY_ID = "SELECT * FROM publisher WHERE id = ?";

    public Publisher getPublisherByResultSet(ResultSet resultSet) throws SQLException {

        Publisher publisher = new Publisher();

        publisher.setId(resultSet.getLong("id"));
        publisher.setName(resultSet.getString("publisher_name"));

        return publisher;
    }


    public Publisher selectPublisherByID(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Publisher publisher = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publisher = getPublisherByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return publisher;
    }

}
