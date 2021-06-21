package com.epam.MangaStore.service;

import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.service.builder.CartItemBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;
import com.epam.MangaStore.util.validator.CartItemValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.MangaStore.constants.Constants.*;

public class CheckOutSelectedService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CartItemBuilder cartItemBuilder = CartItemBuilder.getInstance();
    private CartItemValidator cartItemValidator = CartItemValidator.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        if (request.getParameterValues(CART_ITEM_ID) != null) {

            List<Long> cartItemIDs = Stream.of(request.getParameterValues(CART_ITEM_ID)).map(Long::valueOf)
                    .collect(Collectors.toList());
            List<CartItem> cartItems = cartItemBuilder.fillCartItemsByIDs(cartItemIDs);

            if (cartItems.size() != EMPTY_REQUEST_LENGTH) {
                Long totalPrice = cartItemBuilder.calculateTotalPrice(cartItems);
                request.setAttribute(CART_ITEMS, cartItems);
                request.setAttribute(CART_TOTAL_PRICE, totalPrice);
                dispatcher = request.getRequestDispatcher(CONFIRM_ORDER_JSP);
                dispatcher.forward(request, response);
            }
        } else {
            serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
        }
    }
}
