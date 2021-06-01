package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.service.factory.ServiceFactory;

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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long cartItemID = Long.parseLong(request.getParameter(CART_ITEM_ID));
        // check isExistCartID???
        cartDAO.deleteCartItemByID(cartItemID);
        serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
    }
}
