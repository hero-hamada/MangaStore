package com.epam.MangaStore.service;

import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.builder.CartItemBuilder;
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

public class DisplayCartService implements Service {

    private CartItemBuilder cartItemBuilder = CartItemBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        User user = (User) session.getAttribute(USER);
        List<CartItem> cartItems = cartItemBuilder.fillUserCartItems(user.getId());

        request.setAttribute(CART_ITEMS, cartItems);
        dispatcher = request.getRequestDispatcher(CART_JSP);
        dispatcher.forward(request, response);
    }
}
