package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.GenreDAOImpl;
import com.epam.MangaStore.database.dao.impl.MangaDAOImpl;
import com.epam.MangaStore.database.dao.impl.ReleasingStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
import com.epam.MangaStore.database.dao.interfaces.ReleasingStatusDAO;
import com.epam.MangaStore.entity.Genre;
import com.epam.MangaStore.entity.ReleasingStatus;
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

public class PrepareMangasPageService implements Service {

    private ReleasingStatusDAO releasingStatusDAO = new ReleasingStatusDAOImpl();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private RequestDispatcher dispatcher;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);

        List<ReleasingStatus> releasingStatuses = releasingStatusDAO.selectAll(localeID);
        List<Genre> genres = genreDAO.selectAll(localeID);
        request.setAttribute(GENRES, genres);
        request.setAttribute(RELEASING_STATUSES, releasingStatuses);
        dispatcher = request.getRequestDispatcher(MANGAS_JSP);
        dispatcher.forward(request, response);
    }
}
