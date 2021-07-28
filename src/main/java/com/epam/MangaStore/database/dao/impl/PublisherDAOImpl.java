package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.PublisherDAO;
import com.epam.MangaStore.entity.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PublisherDAOImpl implements PublisherDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_PUBLISHER_BY_ID = "SELECT * FROM publisher WHERE id = ?";
    private static final String SELECT_PUBLISHER_BY_NAME = "SELECT * FROM publisher WHERE name=?";
    private static final String SELECT_ALL = "SELECT * FROM publisher";
    private static final String INSERT_PUBLISHER = "INSERT INTO publisher (name) VALUES (?)";
    private static final String UPDATE_PUBLISHER = "UPDATE publisher SET name=? WHERE id=?";

    private Publisher getPublisherByResultSet(ResultSet resultSet) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(resultSet.getLong("id"));
        publisher.setName(resultSet.getString("name"));
        return publisher;
    }

    @Override
    public Long insert(Publisher publisher) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, publisher.getName());
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
    public void update(Publisher publisher) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PUBLISHER)) {
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.setLong(2, publisher.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    public Publisher selectByID(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Publisher publisher = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publisher = getPublisherByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return publisher;
    }

    @Override
    public List<Publisher> selectAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Publisher> publishers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                publishers.add(getPublisherByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return publishers;
    }

    public Publisher selectPublisherByName(String name) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Publisher publisher = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHER_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publisher = getPublisherByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return publisher;
    }
}
