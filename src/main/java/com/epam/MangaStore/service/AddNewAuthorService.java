package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.AuthorDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.AuthorDAO;
import com.epam.MangaStore.entity.Author;
import com.epam.MangaStore.service.builder.AuthorBuilder;
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

public class AddNewAuthorService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AuthorDAO authorDAO = new AuthorDAOImpl();
    private AuthorBuilder authorBuilder = AuthorBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }
        Author author = authorBuilder.fillNew(request);

        if (author.getFirstName().length() == EMPTY_REQUEST_LENGTH) {
            request.setAttribute(EMPTY_FIELD_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_AUTHORS_SERVICES).execute(request, response);
        } else if (authorDAO.selectIdByFullName(author) != null) {
            request.setAttribute(NOT_UNIQUE_AUTHOR_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_AUTHORS_SERVICES).execute(request, response);
        } else {
            authorDAO.insert(author);
            serviceFactory.getService(DISPLAY_ALL_AUTHORS_SERVICES).execute(request, response);
        }
    }
}
