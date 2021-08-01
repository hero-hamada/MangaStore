package com.epam.MangaStore.service;

import com.epam.MangaStore.constants.SortType;
import com.epam.MangaStore.database.dao.impl.GenreDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.entity.Genre;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.service.builder.MangaBuilder;

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

public class PrepareVolumesPageService implements Service {

    private GenreDAO genreDAO = new GenreDAOImpl();
    private MangaBuilder mangaBuilder = MangaBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
        List<Genre> genres = genreDAO.selectAll(localeID);
        Manga manga = mangaBuilder.fillOneToDisplay(mangaID, localeID);

        request.setAttribute(MANGA, manga);
        request.setAttribute(GENRES, genres);
        request.setAttribute(SORT_TYPES, SortType.values());
        dispatcher = request.getRequestDispatcher(VOLUMES_JSP);
        dispatcher.forward(request, response);
    }
}
