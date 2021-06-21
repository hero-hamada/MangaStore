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

public class AddNewPublisherService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private PublisherBuilder publisherBuilder = PublisherBuilder.getInstance();
    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        Publisher publisher = publisherBuilder.fillNew(request);

        if (publisher.getName().length() == EMPTY_REQUEST_LENGTH) {
            request.setAttribute(EMPTY_FIELD_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_PUBLISHERS_SERVICES).execute(request, response);
        } else if (publisherDAO.selectPublisherByName(publisher.getName()) != null) {
            request.setAttribute(NOT_UNIQUE_PUBLISHER_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_PUBLISHERS_SERVICES).execute(request, response);
        } else {

            publisherDAO.insert(publisher);
            serviceFactory.getService(DISPLAY_ALL_PUBLISHERS_SERVICES).execute(request, response);
        }
    }
}
