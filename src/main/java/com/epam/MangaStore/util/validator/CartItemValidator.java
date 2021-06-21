package com.epam.MangaStore.util.validator;

import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.CartDAO;

import java.sql.SQLException;
import java.util.List;


public class CartItemValidator {

    private static CartItemValidator instance = new CartItemValidator();
    private CartDAO cartDAO = new CartDAOImpl();


    public boolean isCartItemsExist(List<Long> cartItemIDs) throws SQLException {
        for (Long cartItemID : cartItemIDs) {
            if (cartDAO.selectByID(cartItemID) == null) {
                return false;
            }
        }
        return true;
    }

    public static CartItemValidator getInstance() {
        if (instance == null) {
            instance = new CartItemValidator();
        }
        return instance;
    }
}
