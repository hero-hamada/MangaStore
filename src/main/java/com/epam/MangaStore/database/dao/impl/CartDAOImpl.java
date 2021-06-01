package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String INSERT_CART = "INSERT INTO cart (user_id, volume_id, quantity) VALUES (?,?,?)";
    private static final String SELECT_ALL_CART_ITEMS_BY_USER_ID = "SELECT * FROM cart WHERE user_id = ?";
    private static final String SELECT_CART_ITEMS_BY_USER_VOLUME_ID = "SELECT * FROM cart WHERE user_id = ? AND volume_id =?";
    private static final String UPDATE_QUANTITY_BY_ID = "UPDATE cart SET quantity = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM cart WHERE id = ?";

    public CartItem getCartItemByResultSet(ResultSet resultSet) throws SQLException {

        CartItem cartItem = new CartItem();

        cartItem.setId(resultSet.getLong("id"));
        cartItem.setUserID(resultSet.getLong("user_id"));
        cartItem.setVolumeID(resultSet.getLong("volume_id"));
        cartItem.setQuantity(resultSet.getInt("quantity"));

        return cartItem;
    }

    @Override
    public Long insert(CartItem cartItem) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Long generatedID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, cartItem.getUserID());
            preparedStatement.setLong(2, cartItem.getVolume().getId());
            preparedStatement.setInt(3, cartItem.getQuantity());

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

    public List<CartItem> selectCartItemsByUserID(Long userID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<CartItem> cartItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CART_ITEMS_BY_USER_ID)) {
            preparedStatement.setLong(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartItems.add(getCartItemByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return cartItems;
    }

    @Override
    public Boolean isCartItemExistInCart(CartItem cartItem) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Boolean isExist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART_ITEMS_BY_USER_VOLUME_ID)) {
            preparedStatement.setLong(1, cartItem.getUserID());
            preparedStatement.setLong(2, cartItem.getVolume().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExist;
    }

    public void updateCartItemQuantityByID(Long cartItemID, Integer quantity) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY_BY_ID)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setLong(2, cartItemID);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
    public void deleteCartItemByID(Long cartItemID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, cartItemID);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

}
