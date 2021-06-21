package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.CartItem;

import java.sql.SQLException;
import java.util.List;

public interface CartDAO {

    Long insert(CartItem cartItem) throws SQLException;

    CartItem selectByID(Long id) throws SQLException;

    List<CartItem> selectCartItemsByUserID(Long userID) throws SQLException;

    boolean isVolumeExistInCart(CartItem cartItem) throws SQLException;

    void delete(Long cartItemID) throws SQLException;

    void updateQuantity(CartItem cartItem) throws SQLException;
}
