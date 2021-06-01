package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderStatusDAO;
import com.epam.MangaStore.entity.Order;
import com.epam.MangaStore.entity.OrderStatus;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.OrderFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;


public class DisplayAllOrdersService implements Service {

    private OrderFactory orderFactory = OrderFactory.getInstance();
    private OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

//        session.setAttribute(LOCALE_ID, LOCALE_ENGLISH_ID);

        Integer localID = (Integer) session.getAttribute(LOCALE_ID);

        User user = (User) session.getAttribute(USER);

        List<Order> orders = orderFactory.fillAllOrders(localID);
        List<OrderStatus> orderStatuses = orderStatusDAO.selectAll(localID);

        request.setAttribute(ALL_ORDERS, orders);
        request.setAttribute(ALL_ORDER_STATUSES, orderStatuses);

        RequestDispatcher dispatcher = request.getRequestDispatcher(ORDERS_JSP);
        dispatcher.forward(request, response);
    }
}
