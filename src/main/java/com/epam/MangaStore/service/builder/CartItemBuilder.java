package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.entity.Volume;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class CartItemBuilder {

    private static CartItemBuilder instance = new CartItemBuilder();

    private CartDAO cartDAO = new CartDAOImpl();
    private VolumeDAO volumeDAO = new VolumeDAOImpl();

    private CartItemBuilder() {
    }

    public CartItem fillNew(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Volume volume = volumeDAO.selectByID(Long.valueOf(request.getParameter(VOLUME_ID)));

        CartItem cartItem = new CartItem();
        cartItem.setUserID(user.getId());
        cartItem.setVolume(volume);
        cartItem.setQuantity(DEFAULT_CART_ITEM_QUANTITY);
        return cartItem;
    }

    public CartItem fillToUpdate(HttpServletRequest request) throws SQLException {
        CartItem cartItem = cartDAO.selectByID(Long.parseLong(request.getParameter(CART_ITEM_ID)));
        cartItem.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));
        return cartItem;
    }

    public List<CartItem> fillUserCartItems(Long userID) throws SQLException {
        List<CartItem> cartItems = cartDAO.selectCartItemsByUserID(userID);
        for (CartItem cartItem : cartItems) {
            Volume volume = volumeDAO.selectByID(cartItem.getVolumeID());
            cartItem.setUserID(userID);
            cartItem.setVolume(volume);
        }
        return cartItems;
    }

    public List<CartItem> fillCartItemsByIDs(List<Long> cartItemIDs) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem;
        for (Long cartItemID : cartItemIDs) {
            cartItem = cartDAO.selectByID(cartItemID);
            if (cartItem != null)  {
                cartItem.setVolume(volumeDAO.selectByID(cartItem.getVolumeID()));
                cartItems.add(cartItem);
            }
        }
        return cartItems;
    }

    public Long calculateTotalPrice(List<CartItem> cartItems) {
        Long total = 0L;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getVolume().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    public static CartItemBuilder getInstance() {
        if (instance == null) {
            instance = new CartItemBuilder();
        }
        return instance;
    }
}
