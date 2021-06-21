package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.MangaToGenreDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.MangaToGenreDAO;
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

public class DeleteGenreFromMangaService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaToGenreDAO mangaToGenreDAO = new MangaToGenreDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        if (request.getParameter(MANGA_ID).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(GENRE_ID).length() == EMPTY_REQUEST_LENGTH
        ) {
            request.setAttribute(EMPTY_FIELD_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        } else {
            Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
            Integer genreID = Integer.valueOf(request.getParameter(GENRE_ID));
            if (!mangaToGenreDAO.isPairExists(mangaID, genreID)) {
                request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
                serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
            } else {
                mangaToGenreDAO.delete(mangaID, genreID);
                serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
            }
        }
    }
}
