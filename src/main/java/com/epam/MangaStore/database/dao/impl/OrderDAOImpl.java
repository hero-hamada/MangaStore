package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.entity.Author;
import com.epam.MangaStore.entity.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM `order` WHERE user_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM `order`";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM `order` WHERE id=?";
    private static final String INSERT_ORDER = "INSERT INTO `order` (user_id, order_status_id, total_price, created_date)" +
            " VALUES (?,?,?,?)";
    private static final String UPDATE_ORDER_STATUS_BY_ID = "UPDATE `order` SET order_status_id = ? WHERE id = ?";

    private Order getOrderByResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setUserID(resultSet.getLong("user_id"));
        order.setStatusID(resultSet.getInt("order_status_id"));
        order.setTotalPrice(resultSet.getLong("total_price"));
        order.setCreatedDate(resultSet.getDate("created_date"));
        return order;
    }

    @Override
    public Long insert(Order order) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Long generatedID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, order.getUserID());
            preparedStatement.setInt(2, order.getStatusID());
            preparedStatement.setLong(3, order.getTotalPrice());
            preparedStatement.setDate(4, order.getCreatedDate());

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

    public List<Order> selectAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }

    @Override
    public void updateOrderStatus(Long orderID, Integer statusID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_BY_ID)) {
            preparedStatement.setInt(1, statusID);
            preparedStatement.setLong(2, orderID);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Order selectByID(Long orderID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Order order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setLong(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = getOrderByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return order;
    }

    public List<Order> selectUserOrders(Long userID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID)) {
            preparedStatement.setLong(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orders;
    }
}
