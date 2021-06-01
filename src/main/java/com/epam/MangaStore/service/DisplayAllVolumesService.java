package com.epam.MangaStore.service;

import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.service.factory.MangaFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class DisplayAllVolumesService implements Service {

    private MangaFactory mangaFactory = MangaFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
//        session.setAttribute(LOCALE_ID, LOCALE_ENGLISH_ID);

        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);

        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
        Manga manga = mangaFactory.fillMangaToDisplayOne(mangaID, localeID);

        request.setAttribute(MANGA, manga);
        RequestDispatcher dispatcher = request.getRequestDispatcher(VOLUMES_JSP);
        dispatcher.forward(request, response);
    }
}
