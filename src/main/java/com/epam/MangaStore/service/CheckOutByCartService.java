package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.Order;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.CartFactory;
import com.epam.MangaStore.service.factory.OrderFactory;

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

public class CheckOutByCartService implements Service {

    private OrderFactory orderFactory = OrderFactory.getInstance();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private CartFactory cartFactory = CartFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        RequestDispatcher dispatcher;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        if (user == null) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        List<CartItem> cartItems = cartFactory.fillCartItems(user.getId());
        Long totalPrice = cartFactory.calculateTotalPrice(cartItems);
//        Order newOrder = orderFactory.fillNewOrder(request);
        request.setAttribute(CART_ITEMS, cartItems);
        request.setAttribute(CART_TOTAL_PRICE, totalPrice);
        dispatcher = request.getRequestDispatcher(ORDER_JSP);
        dispatcher.forward(request, response);
    }

}
