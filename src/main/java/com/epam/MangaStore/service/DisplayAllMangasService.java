package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.GenreDAOImpl;
import com.epam.MangaStore.database.dao.impl.MangaStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.database.dao.interfaces.MangaStatusDAO;
import com.epam.MangaStore.entity.Genre;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.entity.MangaStatus;
import com.epam.MangaStore.service.factory.MangaFactory;

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

public class DisplayAllMangasService implements Service {

    private MangaFactory mangaFactory = MangaFactory.getInstance();

    private MangaStatusDAO mangaStatusDAO = new MangaStatusDAOImpl();
    private GenreDAO genreDAO = new GenreDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

//        session.setAttribute(LOCALE_ID, LOCALE_ENGLISH_ID);

        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);

        List<Manga> mangas = mangaFactory.fillMangasToDisplayAll(localeID);
        List<MangaStatus> mangaStatuses = mangaStatusDAO.selectAll(localeID);
        List<Genre> genres = genreDAO.selectAll(localeID);

        request.setAttribute(ALL_MANGAS, mangas);
        request.setAttribute(ALL_GENRES, genres);
        request.setAttribute(ALL_MANGA_STATUSES, mangaStatuses);
        RequestDispatcher dispatcher = request.getRequestDispatcher(MANGAS_JSP);
        dispatcher.forward(request, response);
    }
}
