package com.epam.MangaStore.util.validator;


import javax.servlet.http.HttpServletRequest;

import static com.epam.MangaStore.constants.Constants.*;
import static com.epam.MangaStore.constants.Constants.EMPTY_REQUEST_LENGTH;

public class MangaValidator {

    private static MangaValidator instance = new MangaValidator();

    public static MangaValidator getInstance() {
        if (instance == null) {
            instance = new MangaValidator();
        }
        return instance;
    }

    public boolean isEmptyParamExists(HttpServletRequest request) {
        return request.getParameter(TITLE).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(DESCRIPTION).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(LANGUAGE_ID).length() == EMPTY_REQUEST_LENGTH ||
                request.getParameter(PUBLISHER_NAME).length() == EMPTY_REQUEST_LENGTH;
    }
}
