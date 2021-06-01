package com.epam.MangaStore.service;

import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.CartFactory;

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

    private CartFactory cartFactory = CartFactory.getInstance();

    // should I check roleID, is_banned, user A: YES!
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

//        session.setAttribute(LOCALE_ID, LOCALE_ENGLISH_ID);

        User user = (User) session.getAttribute(USER);

        List<CartItem> cartItems = cartFactory.fillCartItems(user.getId());

        request.setAttribute(CART_ITEMS, cartItems);

        RequestDispatcher dispatcher = request.getRequestDispatcher(CART_JSP);
        dispatcher.forward(request, response);
    }
}
