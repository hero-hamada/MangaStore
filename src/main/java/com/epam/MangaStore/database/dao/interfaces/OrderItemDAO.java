package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDAO {
    Long insert(OrderItem orderItem) throws SQLException;
    List<OrderItem> selectAllByOrderID(Long orderID) throws SQLException;
}
