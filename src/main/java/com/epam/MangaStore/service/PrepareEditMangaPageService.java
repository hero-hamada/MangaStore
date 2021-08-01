package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.MangaDAOImpl;
import com.epam.MangaStore.database.dao.impl.ReleasingStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
import com.epam.MangaStore.database.dao.interfaces.ReleasingStatusDAO;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.entity.ReleasingStatus;
import com.epam.MangaStore.service.builder.MangaBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class PrepareEditMangaPageService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaBuilder mangaBuilder = MangaBuilder.getInstance();
    private ReleasingStatusDAO releasingStatusDAO = new ReleasingStatusDAOImpl();
    private MangaDAO mangaDAO = new MangaDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));

        if (mangaDAO.selectByID(mangaID) == null) {
            serviceFactory.getService(SORT_VOLUMES_SERVICE).execute(request, response);
        } else {
            Integer localID = (Integer) session.getAttribute(LOCALE_ID);
            List<ReleasingStatus> releasingStatuses = releasingStatusDAO.selectAll(localID);
            Manga manga = mangaBuilder.fillOneToDisplay(mangaID, localID);

            request.setAttribute(RELEASING_STATUSES, releasingStatuses);
            request.setAttribute(MANGA, manga);
            dispatcher = request.getRequestDispatcher(EDIT_MANGA_JSP);
            dispatcher.forward(request, response);
        }
    }
}
