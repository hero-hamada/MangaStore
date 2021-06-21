package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.builder.CartItemBuilder;
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

public class SetQuantityService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CartItemBuilder cartItemBuilder = CartItemBuilder.getInstance();
    private CartDAO cartDAO = new CartDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        User user = (User) session.getAttribute(USER);
        CartItem cartItem = cartItemBuilder.fillToUpdate(request);

        if (cartItem.getQuantity() > DEFAULT_CART_ITEM_QUANTITY && cartItem.getUserID().equals(user.getId())) {
            cartDAO.updateQuantity(cartItem);
        } else {
            request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
        }
        serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
    }
}
