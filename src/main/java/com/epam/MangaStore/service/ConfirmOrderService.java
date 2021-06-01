package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.impl.OrderItemDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.database.dao.interfaces.OrderItemDAO;
import com.epam.MangaStore.entity.Order;
import com.epam.MangaStore.entity.OrderItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.CartFactory;
import com.epam.MangaStore.service.factory.OrderFactory;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.service.factory.VolumeFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class ConfirmOrderService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderFactory orderFactory = OrderFactory.getInstance();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private CartFactory cartFactory = CartFactory.getInstance();
    private VolumeFactory volumeFactory = VolumeFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        RequestDispatcher dispatcher;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        if (user == null) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        Order newOrder = orderFactory.fillNewOrder(request);

//         using return methods like voids
        Long newOrderID = orderDAO.insert(newOrder);

        for (OrderItem orderItem : newOrder.getOrderItems()) {
            orderItem.setOrderID(newOrderID);
            orderItemDAO.insert(orderItem);
        }
        request.setAttribute(ORDER, newOrder);
        serviceFactory.getService(DISPLAY_MY_ORDERS_SERVICES).execute(request, response);

    }
}
