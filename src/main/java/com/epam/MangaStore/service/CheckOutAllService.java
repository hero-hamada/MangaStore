package com.epam.MangaStore.service;

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
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class CheckOutAllService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CartItemBuilder cartItemBuilder = CartItemBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        List<CartItem> cartItems = cartItemBuilder.fillUserCartItems(user.getId());

        if (cartItems.size() == EMPTY_REQUEST_LENGTH) {
            serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
        }

        Long totalPrice = cartItemBuilder.calculateTotalPrice(cartItems);
        request.setAttribute(CART_ITEMS, cartItems);
        request.setAttribute(CART_TOTAL_PRICE, totalPrice);
        dispatcher = request.getRequestDispatcher(CONFIRM_ORDER_JSP);
        dispatcher.forward(request, response);
    }

}
