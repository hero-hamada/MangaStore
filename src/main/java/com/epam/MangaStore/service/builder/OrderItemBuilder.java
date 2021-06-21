package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.database.dao.impl.OrderItemDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderItemDAO;
import com.epam.MangaStore.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemBuilder {

    private static OrderItemBuilder instance = new OrderItemBuilder();

    private CartItemBuilder cartItemBuilder = CartItemBuilder.getInstance();
    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    private OrderItemBuilder() {
    }

    public List<OrderItem> fillNewOrderItems(List<Long> cartItemIDs) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        List<CartItem> cartItems = cartItemBuilder.fillCartItemsByIDs(cartItemIDs);
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
            orderItem.setVolume(volumeBuilder.fillOneToDisplay(orderItem.getVolumeID()));
        }
        return orderItems;
    }

    public static OrderItemBuilder getInstance() {
        if (instance == null) {
            instance = new OrderItemBuilder();
        }
        return instance;
    }
}
