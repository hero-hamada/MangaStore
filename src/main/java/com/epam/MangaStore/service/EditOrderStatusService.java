package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.impl.OrderStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.database.dao.interfaces.OrderStatusDAO;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class EditOrderStatusService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        Integer orderStatusID = Integer.valueOf(request.getParameter(ORDER_STATUS_ID));
        Long orderID = Long.valueOf(request.getParameter(ORDER_ID));

        if (orderDAO.selectByID(orderID) != null && orderStatusDAO.selectByID(orderStatusID, localeID) != null) {
            orderDAO.updateOrderStatus(orderID, orderStatusID);
        }

        serviceFactory.getService(DISPLAY_ALL_ORDERS_SERVICES).execute(request, response);
    }
}
