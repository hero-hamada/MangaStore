package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface OrderStatusDAO {
    OrderStatus selectOrderStatusByID(Integer statusID, Integer sessionLanguageID) throws SQLException;
    List<OrderStatus> selectAll(Integer sessionLanguageID) throws SQLException;

}
