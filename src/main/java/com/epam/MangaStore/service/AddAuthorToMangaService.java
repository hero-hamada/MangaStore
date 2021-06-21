package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.AuthorDAOImpl;
import com.epam.MangaStore.database.dao.impl.MangaDAOImpl;
import com.epam.MangaStore.database.dao.impl.MangaToAuthorDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.AuthorDAO;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
import com.epam.MangaStore.database.dao.interfaces.MangaToAuthorDAO;
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

public class AddAuthorToMangaService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaToAuthorDAO mangaToAuthorDAO = new MangaToAuthorDAOImpl();
    private AuthorDAO authorDAO = new AuthorDAOImpl();
    private MangaDAO mangaDAO = new MangaDAOImpl();
    private AuthorBuilder authorBuilder = AuthorBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        Long authorID = authorDAO.selectIdByFullName(authorBuilder.fillNew(request));
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));

        if (mangaDAO.selectByID(mangaID) == null) {
            request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        } else if (authorID == null) {
            request.setAttribute(AUTHOR_NAME_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        } else if (mangaToAuthorDAO.isPairExists(mangaID, authorID)) {
            request.setAttribute(NOT_UNIQUE_MANGA_AUTHOR_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        } else {
            mangaToAuthorDAO.insert(mangaID, authorID);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        }
    }
}
