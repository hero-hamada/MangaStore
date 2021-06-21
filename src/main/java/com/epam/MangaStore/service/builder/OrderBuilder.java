package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.impl.OrderStatusDAOImpl;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.database.dao.interfaces.OrderStatusDAO;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.entity.*;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

import static com.epam.MangaStore.constants.Constants.*;

public class OrderBuilder {

    private static OrderBuilder instance = new OrderBuilder();

    private OrderItemBuilder orderItemBuilder = OrderItemBuilder.getInstance();
    private OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();

    private OrderBuilder() {
    }

    public Order fillNew(Long userID, List<Long> cartItemIDs) throws SQLException {

        java.sql.Date createdDate = java.sql.Date.valueOf(LocalDate.now());
        List<OrderItem> orderItems = orderItemBuilder.fillNewOrderItems(cartItemIDs);

        Order order = new Order();
        order.setUserID(userID);
        order.setStatusID(ORDER_STATUS_IN_PROCESSING);
        order.setTotalPrice(calculateTotalPrice(orderItems));
        order.setCreatedDate(createdDate);
        order.setOrderItems(orderItems);

        return order;
    }

    public List<Order> fillUserOrders(Long userID, Integer localID) throws SQLException {
        List<Order> orders = orderDAO.selectUserOrders(userID);
        for (Order order : orders) {
            order.setOrderItems(orderItemBuilder.fillOrderItems(order.getId()));
            order.setStatus(orderStatusDAO.selectByID(order.getStatusID(), localID));
        }
        return orders;
    }

    public List<Order> fillAllOrders(Integer localID) throws SQLException {
        List<Order> orders = orderDAO.selectAll();
        for (Order order : orders) {

            order.setOrderItems(orderItemBuilder.fillOrderItems(order.getId()));
            order.setStatus(orderStatusDAO.selectByID(order.getStatusID(), localID));
            order.setUser(userDAO.selectByID(order.getUserID()));
        }
        return orders;
    }

    private Long calculateTotalPrice(List<OrderItem> orderItems) {
        Long total = 0L;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getFixedPrice() * orderItem.getQuantity();
        }
        return total;
    }

    public static OrderBuilder getInstance() {
        if (instance == null) {
            instance = new OrderBuilder();
        }
        return instance;
    }
}
