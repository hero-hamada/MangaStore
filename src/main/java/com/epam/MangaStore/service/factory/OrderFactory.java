package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.impl.OrderStatusDAOImpl;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.database.dao.impl.VolumeDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.database.dao.interfaces.OrderStatusDAO;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.*;
import com.epam.MangaStore.util.ImageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

import static com.epam.MangaStore.constants.Constants.*;

public class OrderFactory {

    private static OrderFactory instance = new OrderFactory();
    private OrderItemFactory orderItemFactory = OrderItemFactory.getInstance();
    private OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();

    private OrderFactory() {
    }

    public Order fillNewOrder(HttpServletRequest request) throws SQLException {
    //??
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Integer localID = (Integer) session.getAttribute(LOCALE_ID);
        java.sql.Date createdDate =  java.sql.Date.valueOf(LocalDate.now());
        List<OrderItem> orderItems = orderItemFactory.fillNewOrderItemsByCart(user.getId());

        Order order = new Order();
        order.setUserID(user.getId());
        order.setStatusID(ORDER_STATUS_IN_PROCESSING);
        order.setTotalPrice(calculateTotalPrice(orderItems));
        order.setCreatedDate(createdDate);
        order.setOrderItems(orderItems);

        return order;
    }

    public List<Order> fillUserOrders(Long userID, Integer localID) throws SQLException {
        List<Order> orders = orderDAO.selectUserOrders(userID);
        for (Order order : orders) {

            order.setOrderItems(orderItemFactory.fillOrderItems(order.getId()));
            order.setStatus(orderStatusDAO.selectOrderStatusByID(order.getStatusID(), localID));
        }
        return orders;
    }

    public List<Order> fillAllOrders(Integer localID) throws SQLException {
        List<Order> orders = orderDAO.selectAll();
        for (Order order : orders) {

            order.setOrderItems(orderItemFactory.fillOrderItems(order.getId()));
            order.setStatus(orderStatusDAO.selectOrderStatusByID(order.getStatusID(), localID));
            order.setUser(userDAO.selectUserByID(order.getUserID()));
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

    public static OrderFactory getInstance() {
        if (instance == null) {
            instance = new OrderFactory();
        }
        return instance;
    }
}
