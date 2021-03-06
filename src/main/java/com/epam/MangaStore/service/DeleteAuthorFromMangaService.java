package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.MangaToAuthorDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.MangaToAuthorDAO;
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
import static com.epam.MangaStore.constants.Constants.SORT_VOLUMES_SERVICE;

public class DeleteAuthorFromMangaService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaToAuthorDAO mangaToAuthorDAO = new MangaToAuthorDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        } else {
            Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
            Long authorID = Long.valueOf(request.getParameter(AUTHOR_ID));
            if (mangaToAuthorDAO.isPairExists(mangaID, authorID)) {
                mangaToAuthorDAO.delete(mangaID, authorID);
            }
            serviceFactory.getService(SORT_VOLUMES_SERVICE).execute(request, response);
        }
    }
}
