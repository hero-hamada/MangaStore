package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;
import static com.epam.MangaStore.constants.Constants.DISPLAY_ALL_USERS_SERVICE;

public class EditOrderStatusService implements Service {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        Integer orderStatusID = Integer.valueOf(request.getParameter(ORDER_STATUS_ID));
        Long orderID = Long.valueOf(request.getParameter(ORDER_ID));

        orderDAO.updateOrderStatus(orderID, orderStatusID);

        serviceFactory.getService(DISPLAY_ALL_ORDERS_SERVICES).execute(request, response);
    }
}
