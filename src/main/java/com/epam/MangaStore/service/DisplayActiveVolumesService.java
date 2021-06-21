package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.MangaDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
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

public class DisplayActiveVolumesService implements Service {

    private MangaBuilder mangaBuilder = MangaBuilder.getInstance();
    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();
    private RequestDispatcher dispatcher;
    private MangaDAO mangaDAO = new MangaDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));

        if (!AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session) || mangaDAO.isMangaDeleted(mangaID)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        List<Volume> volumes = volumeBuilder.fillActivesToDisplayAll(mangaID);
        Manga manga = mangaBuilder.fillOneToDisplay(mangaID, localeID);
        manga.setVolumes(volumes);

        request.setAttribute(MANGA, manga);
        RequestDispatcher dispatcher = request.getRequestDispatcher(VOLUMES_JSP);
        dispatcher.forward(request, response);
    }
}
