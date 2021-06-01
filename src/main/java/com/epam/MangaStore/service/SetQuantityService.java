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

public class SetQuantityService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CartDAO cartDAO = new CartDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        // valueOf orrrr parseX?
        Long cartItemID = Long.parseLong(request.getParameter(CART_ITEM_ID));
        Integer quantity = Integer.parseInt(request.getParameter(QUANTITY));

        if (quantity >= DEFAULT_VOLUME_QUANTITY) {
            cartDAO.updateCartItemQuantityByID(cartItemID, quantity);
        }
        serviceFactory.getService(DISPLAY_CART_SERVICE).execute(request, response);
    }
}
