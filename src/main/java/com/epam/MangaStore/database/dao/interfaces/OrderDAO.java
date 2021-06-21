package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

    Long insert(Order order) throws SQLException;

    List<Order> selectUserOrders(Long userID) throws SQLException;

    List<Order> selectAll() throws SQLException;

    void updateOrderStatus(Long orderID, Integer statusID) throws SQLException;

    Order selectByID(Long orderID) throws SQLException;
}
