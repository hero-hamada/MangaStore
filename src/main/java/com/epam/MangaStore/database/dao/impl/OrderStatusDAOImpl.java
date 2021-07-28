package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.OrderStatusDAO;
import com.epam.MangaStore.entity.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDAOImpl implements OrderStatusDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ORDER_STATUS_BY_ID = "SELECT * FROM order_status WHERE id = ? AND language_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM order_status WHERE language_id = ?";


    private OrderStatus getMangaStatusByResultSet(ResultSet resultSet) throws SQLException {
        OrderStatus mangaStatus = new OrderStatus();
        mangaStatus.setId(resultSet.getLong("id"));
        mangaStatus.setLanguageID(resultSet.getInt("language_id"));
        mangaStatus.setName(resultSet.getString("name"));
        return mangaStatus;
    }

    public List<OrderStatus> selectAll(Integer sessionLanguageID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<OrderStatus> orderStatuses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setInt(1, sessionLanguageID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderStatuses.add(getMangaStatusByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orderStatuses;
    }

    public OrderStatus selectOrderStatusByID(Integer statusID, Integer localeID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        OrderStatus orderStatus = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_STATUS_BY_ID)) {
            preparedStatement.setLong(1, statusID);
            preparedStatement.setInt(2, localeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderStatus = getMangaStatusByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orderStatus;
    }
}