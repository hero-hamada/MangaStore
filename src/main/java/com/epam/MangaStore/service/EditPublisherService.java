package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.PublisherDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.PublisherDAO;
import com.epam.MangaStore.entity.Publisher;
import com.epam.MangaStore.service.builder.PublisherBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class EditPublisherService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    private PublisherBuilder publisherBuilder = PublisherBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }
        if (request.getParameter(PUBLISHER_NAME).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(PUBLISHER_ID).length() == EMPTY_REQUEST_LENGTH
        ) {
            request.setAttribute(EMPTY_FIELD_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_PUBLISHERS_SERVICES).execute(request, response);
        } else {
            Publisher publisher = publisherBuilder.fillToUpdate(request);
            publisherDAO.update(publisher);
            serviceFactory.getService(DISPLAY_ALL_PUBLISHERS_SERVICES).execute(request, response);
        }
    }
}
