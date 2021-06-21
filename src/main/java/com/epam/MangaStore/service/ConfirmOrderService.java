package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.OrderDAOImpl;
import com.epam.MangaStore.database.dao.impl.OrderItemDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.OrderDAO;
import com.epam.MangaStore.database.dao.interfaces.OrderItemDAO;
import com.epam.MangaStore.entity.Order;
import com.epam.MangaStore.entity.OrderItem;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.builder.OrderBuilder;
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

public class ConfirmOrderService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private OrderBuilder orderBuilder = OrderBuilder.getInstance();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private CartItemValidator cartItemValidator = CartItemValidator.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, session)) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        List<Long> cartItemIDs = Stream.of(request.getParameterValues(CART_ITEM_ID)).map(Long::valueOf)
                .collect(Collectors.toList());
        User user = (User) session.getAttribute(USER);

        if (!cartItemValidator.isCartItemsExist(cartItemIDs)) {
            request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
        } else {
            Order newOrder = orderBuilder.fillNew(user.getId(), cartItemIDs);
            Long newOrderID = orderDAO.insert(newOrder);

            for (OrderItem orderItem : newOrder.getOrderItems()) {
                orderItem.setOrderID(newOrderID);
                orderItemDAO.insert(orderItem);
            }
            request.setAttribute(ORDER, newOrder);
            serviceFactory.getService(DISPLAY_MY_ORDERS_SERVICES).execute(request, response);
        }
    }
}
