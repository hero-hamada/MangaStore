package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.OrderItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.entity.Volume;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class CartFactory {

    private static CartFactory instance = new CartFactory();

    private CartDAO cartDAO = new CartDAOImpl();
    private VolumeDAO volumeDAO = new VolumeDAOImpl();

    private CartFactory() {
    }

    // or fillCartItemToAdd(userID, volumeID)
    public CartItem fillNewCartItem(HttpServletRequest request) throws SQLException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        // should I handle ParseException and check volume for null??
        Volume volume = volumeDAO.selectVolumeByID(Long.valueOf(request.getParameter(VOLUME_ID)));

        CartItem cartItem = new CartItem();
        cartItem.setUserID(user.getId());
        cartItem.setVolume(volume);
        cartItem.setQuantity(DEFAULT_VOLUME_QUANTITY);

        return cartItem;
    }

    public List<CartItem> fillCartItems(Long userID) throws SQLException {

        List<CartItem> cartItems = cartDAO.selectCartItemsByUserID(userID);

        for (CartItem cartItem : cartItems) {
            Volume volume = volumeDAO.selectVolumeByID(cartItem.getVolumeID());

            cartItem.setUserID(userID);
            cartItem.setVolume(volume);
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

    public static CartFactory getInstance() {
        if (instance == null) {
            instance = new CartFactory();
        }
        return instance;
    }

}
