package com.epam.MangaStore.service;

import com.epam.MangaStore.entity.Order;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.builder.OrderBuilder;
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

public class DisplayMyOrdersService implements Service {

        private OrderBuilder orderBuilder = OrderBuilder.getInstance();
        private RequestDispatcher dispatcher;

        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

            HttpSession session = request.getSession();
            Integer localeID = (Integer) session.getAttribute(LOCALE_ID);

            if (AccessValidator.isAccessDenied(ROLE_USER_ID, session)) {
                dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
                dispatcher.forward(request, response);
            }

            User user = (User) session.getAttribute(USER);
            List<Order> orders = orderBuilder.fillUserOrders(user.getId(), localeID);

            request.setAttribute(USER_ORDERS, orders);
            dispatcher = request.getRequestDispatcher(MY_ORDERS_JSP);
            dispatcher.forward(request, response);
        }
}
