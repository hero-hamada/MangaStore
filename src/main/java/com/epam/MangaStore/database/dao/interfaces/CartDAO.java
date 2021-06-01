package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.CartItem;

import java.sql.SQLException;
import java.util.List;

public interface CartDAO {
    Long insert(CartItem cartItem) throws SQLException;
    List<CartItem> selectCartItemsByUserID(Long userID) throws SQLException;
    Boolean isCartItemExistInCart(CartItem cartItem) throws SQLException;
    void updateCartItemQuantityByID(Long cartItemID, Integer quantity) throws SQLException;
    void deleteCartItemByID(Long cartItemID) throws SQLException;
}
