package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;
import static com.epam.MangaStore.constants.Constants.DISPLAY_CART_SERVICE;

public class DeleteCartItemService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CartDAO cartDAO = new CartDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }
        Long cartItemID = Long.valueOf(request.getParameter(CART_ITEM_ID));

        if (cartDAO.selectByID(cartItemID) != null) {
            cartDAO.delete(cartItemID);
        } else {
            request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
        }
        serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
    }
}
