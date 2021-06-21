package com.epam.MangaStore.util.validator;

import com.epam.MangaStore.database.dao.impl.GenreDAOImpl;
import com.epam.MangaStore.database.dao.impl.MangaToGenreDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.database.dao.interfaces.MangaToGenreDAO;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class GenreValidator {

    private static GenreValidator instance = new GenreValidator();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private MangaToGenreDAO mangaToGenreDAO = new MangaToGenreDAOImpl();

    public static GenreValidator getInstance() {
        if (instance == null) {
            instance = new GenreValidator();
        }
        return instance;
    }

    public boolean isEmptyParamExists(HttpServletRequest request) {
        return request.getParameterValues(GENRE_ID) == null ||
                request.getParameter(MANGA_ID).length() == EMPTY_REQUEST_LENGTH;
    }

    public boolean isGenresExist(List<Integer> genreIDs) throws SQLException {
        for (Integer genreID : genreIDs) {
            if (genreDAO.selectByID(genreID) == null) {
                return false;
            }
        }
        return true;
    }
}
