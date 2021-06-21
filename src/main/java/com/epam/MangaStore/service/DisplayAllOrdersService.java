package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderStatusDAO;
import com.epam.MangaStore.entity.Order;
import com.epam.MangaStore.entity.OrderStatus;
import com.epam.MangaStore.service.builder.OrderBuilder;
import com.epam.MangaStore.util.validator.AccessValidator;

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

    private OrderBuilder orderBuilder = OrderBuilder.getInstance();
    private OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        List<Order> orders = orderBuilder.fillAllOrders(localeID);
        List<OrderStatus> orderStatuses = orderStatusDAO.selectAll(localeID);

        request.setAttribute(ORDERS, orders);
        request.setAttribute(ORDER_STATUSES, orderStatuses);
        dispatcher = request.getRequestDispatcher(ORDERS_JSP);
        dispatcher.forward(request, response);
    }
}
