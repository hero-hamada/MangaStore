package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.MangaDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
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

public class PrepareAddVolumePageService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaDAO mangaDAO = new MangaDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));

        if (mangaDAO.selectByID(mangaID) == null) {
            serviceFactory.getService(SORT_VOLUMES_SERVICE).execute(request, response);
        } else {
            request.setAttribute(MANGA_ID, mangaID);
            dispatcher = request.getRequestDispatcher(ADD_VOLUME_JSP);
            dispatcher.forward(request, response);
        }
    }
}
