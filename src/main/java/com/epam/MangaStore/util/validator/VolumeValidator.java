package com.epam.MangaStore.util.validator;

import com.epam.MangaStore.database.dao.impl.CoverDAOImpl;
import com.epam.MangaStore.database.dao.impl.VolumeDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.CoverDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.util.ImageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static com.epam.MangaStore.constants.Constants.*;

public class VolumeValidator {

    private final static String ISBN_REGEX = "(?=[0-9]{13}$)|97[89][-.]?[0-9]{10}";
    private final static String FORMAT_REGEX = "[0-9.]+x[0-9.]+/[0-9.]+";
    private final static String SIZE_REGEX = "[0-9.]+[ ]?x[ ]?[0-9.]+[ ]?x[ ]?[0-9.]+";
    private final static Integer MIN_FOR_POSITIVE_VALUES = 0;

    private static VolumeValidator instance = new VolumeValidator();
    private VolumeDAO volumeDAO = new VolumeDAOImpl();
    private CoverDAO coverDAO = new CoverDAOImpl();

    public boolean isISBNInvalid(String isbn) throws SQLException {
        return !Pattern.matches(ISBN_REGEX, isbn) || volumeDAO.isISBNExists(isbn);
    }

    public boolean isFormatInvalid(String isbn) {
        return !Pattern.matches(FORMAT_REGEX, isbn);
    }

    public boolean isSizeFormatInvalid(String size) {
        return !Pattern.matches(SIZE_REGEX, size);
    }

    public boolean isNumberInvalid(Long mangaID, Integer volumeNumber) throws SQLException {
        if (volumeNumber < MIN_FOR_POSITIVE_VALUES) {
            return volumeDAO.isMangaIDAndNumberExists(mangaID, volumeNumber);
        }
        return false;
    }

    public boolean isEmptyParamExists(HttpServletRequest request) {
        return request.getParameter(TITLE).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(PAGE_COUNT).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(PRICE).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(RELEASE_DATE).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(NUMBER).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(BINDING).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(SIZE).length() == EMPTY_REQUEST_LENGTH;
    }

    public boolean isPositiveParamsInvalid(Volume volume) {
        return volume.getPrice() < MIN_FOR_POSITIVE_VALUES ||
                volume.getNumber() <= MIN_FOR_POSITIVE_VALUES;
    }

    public static VolumeValidator getInstance() {
        if (instance == null) {
            instance = new VolumeValidator();
        }
        return instance;
    }
}

