package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.VolumeDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.CartDAO;
import com.epam.MangaStore.database.dao.impl.CartDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.CartItem;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.service.builder.CartItemBuilder;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class AddToCartService implements Service {

    private CartItemBuilder cartItemBuilder = CartItemBuilder.getInstance();

    private CartDAO cartDAO = new CartDAOImpl();
    private VolumeDAO volumeDAO = new VolumeDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_USER_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }
        Volume volume = volumeDAO.selectByID(Long.valueOf(request.getParameter(VOLUME_ID)));

        if (volume == null || volume.getAccessStatusID().equals(ACCESS_STATUS_DELETED_ID)) {
            request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
        } else {
            CartItem cartItem = cartItemBuilder.fillNew(request);
            if (!cartDAO.isVolumeExistInCart(cartItem)) {
                cartDAO.insert(cartItem);
            }
        }

        response.sendRedirect(request.getHeader(REFERER));
    }
}