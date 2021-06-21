package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.MangaDAOImpl;
import com.epam.MangaStore.database.dao.impl.MangaToGenreDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.MangaDAO;
import com.epam.MangaStore.database.dao.interfaces.MangaToGenreDAO;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.GenreValidator;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.MangaStore.constants.Constants.*;

public class AddGenreToMangaService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaToGenreDAO mangaToGenreDAO = new MangaToGenreDAOImpl();
    private MangaDAO mangaDAO = new MangaDAOImpl();
    private GenreValidator genreValidator = GenreValidator.getInstance();
    private RequestDispatcher dispatcher;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        if (genreValidator.isEmptyParamExists(request)) {
            request.setAttribute(EMPTY_FIELD_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        } else {
            List<Integer> genreIDs = Stream.of(request.getParameterValues(GENRE_ID)).map(Integer::valueOf)
                    .collect(Collectors.toList());
            Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));

            if (genreValidator.isGenresExist(genreIDs) && mangaDAO.selectByID(mangaID) != null) {
                for (Integer genreID : genreIDs) {
                    if (!mangaToGenreDAO.isPairExists(mangaID, genreID))
                        mangaToGenreDAO.insert(mangaID, genreID);
                }
                serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
            }
            request.setAttribute(HIDDEN_INPUT_ERROR, ERROR_OCCURRED);
            serviceFactory.getService(DISPLAY_ALL_VOLUMES_SERVICE).execute(request, response);
        }
    }
}

