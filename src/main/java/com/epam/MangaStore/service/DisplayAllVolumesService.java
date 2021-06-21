package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.GenreDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.entity.Genre;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.service.builder.MangaBuilder;
import com.epam.MangaStore.service.builder.VolumeBuilder;
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

public class DisplayAllVolumesService implements Service {

    private MangaBuilder mangaBuilder = MangaBuilder.getInstance();
    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));

        List<Volume> volumes = volumeBuilder.fillAllToDisplay(mangaID);
        Manga manga = mangaBuilder.fillOneToDisplay(mangaID, localeID);
        List<Genre> genres = genreDAO.selectAll(localeID);

        manga.setVolumes(volumes);
        request.setAttribute(MANGA, manga);
        request.setAttribute(GENRES, genres);
        dispatcher = request.getRequestDispatcher(VOLUMES_JSP);
        dispatcher.forward(request, response);
    }
}
