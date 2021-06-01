package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.CartFactory;
import com.epam.MangaStore.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class AddToCartService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CartFactory cartFactory = CartFactory.getInstance();

    private CartDAO cartDAO = new CartDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        RequestDispatcher dispatcher;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        if (user == null) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        CartItem cartItem = cartFactory.fillNewCartItem(request);

        if (!cartDAO.isCartItemExistInCart(cartItem)) {
            cartDAO.insert(cartItem);
        }

        serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);

    }
}