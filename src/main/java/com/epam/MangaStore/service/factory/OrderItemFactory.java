package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.database.dao.impl.OrderItemDAOImpl;
import com.epam.MangaStore.database.dao.impl.VolumeDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.interfaces.OrderItemDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class OrderItemFactory {

    private static OrderItemFactory instance = new OrderItemFactory();
    private CartFactory cartFactory = CartFactory.getInstance();
    private VolumeFactory volumeFactory = VolumeFactory.getInstance();
    private CartDAO cartDAO = new CartDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    private OrderItemFactory() {
    }

    public List<OrderItem> fillNewOrderItemsByCart(Long userID) throws SQLException {

        List<OrderItem> orderItems = new ArrayList<>();

        List<CartItem> cartItems = cartFactory.fillCartItems(userID);
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setVolumeID(cartItem.getVolumeID());
            orderItem.setVolume(cartItem.getVolume());
            orderItem.setFixedPrice(cartItem.getVolume().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    public List<OrderItem> fillOrderItems(Long orderID) throws SQLException {
        List<OrderItem> orderItems = orderItemDAO.selectAllByOrderID(orderID);
        for (OrderItem orderItem : orderItems) {
            orderItem.setVolume(volumeFactory.fillVolume(orderItem.getVolumeID()));
        }
        return orderItems;
    }

//    public OrderItem fillNewOrderItem() throws SQLException {
//
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute(USER);
//
//        Volume volume = volumeDAO.selectVolumeByID(Long.valueOf(request.getParameter(VOLUME_ID)));
//
//        CartItem cartItem = new CartItem();
//        cartItem.setUserID(user.getID());
//        cartItem.setVolume(volume);
//        cartItem.setQuantity(DEFAULT_VOLUME_QUANTITY);
//
//        return orderItem;
//    }
    public static OrderItemFactory getInstance() {
        if (instance == null) {
            instance = new OrderItemFactory();
        }
        return instance;
    }
}
