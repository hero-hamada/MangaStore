package com.epam.MangaStore.service;


import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.service.builder.MangaBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.MangaStore.constants.Constants.*;

public class FilterMangaService implements Service {

    private MangaBuilder mangaBuilder = MangaBuilder.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        HttpSession session = request.getSession();
        Integer localeID = (Integer) session.getAttribute(LOCALE_ID);
        List<Manga> mangas;

        if (request.getParameterValues(GENRE_ID) != null ) {
            List<Integer> genreIDs = Stream.of(request.getParameterValues(GENRE_ID)).map(Integer::valueOf)
                    .collect(Collectors.toList());
            mangas = mangaBuilder.fillByFilter(genreIDs, localeID);
            request.setAttribute(CHECKED_GENRE_IDS, genreIDs);
        } else {
            mangas = mangaBuilder.fillAllToDisplay(localeID);
        }

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            mangas = mangaBuilder.getActive(mangas);
        }

        request.setAttribute(MANGAS, mangas);

        serviceFactory.getService(PREPARE_MANGAS_PAGE_SERVICE).execute(request, response);
    }
}
