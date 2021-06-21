package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.OrderItemDAO;
import com.epam.MangaStore.entity.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_BY_ORDER_ID =
            "SELECT * FROM order_item WHERE order_id = ?";
    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_item (order_id, volume_id, fixed_price, quantity)" +
            " VALUES (?,?,?,?)";

    private OrderItem getOrderItemByResultSet(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getLong("id"));
        orderItem.setOrderID(resultSet.getLong("order_id"));
        orderItem.setVolumeID(resultSet.getLong("volume_id"));
        orderItem.setFixedPrice(resultSet.getLong("fixed_price"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        return orderItem;
    }

    @Override
    public Long insert(OrderItem orderItem) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setLong(1, orderItem.getOrderID());
            preparedStatement.setLong(2, orderItem.getVolumeID());
            preparedStatement.setLong(3, orderItem.getFixedPrice());
            preparedStatement.setInt(4, orderItem.getQuantity());
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

    public List<OrderItem> selectAllByOrderID(Long orderID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_ORDER_ID)) {
            preparedStatement.setLong(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderItems.add(getOrderItemByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return orderItems;
    }
}
