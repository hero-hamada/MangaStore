package com.epam.MangaStore.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ErrorMessageProvider {

    private static final String FILENAME = "localization";
    private static final String LOCALE = "locale";

    private static ResourceBundle BUNDLE;

    private ErrorMessageProvider(){
        throw new UnsupportedOperationException();
    }

    public static String getErrorMessage(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();

        String language = (String) session.getAttribute(LOCALE);
        Locale sessionLocal = new Locale(language);
        BUNDLE = ResourceBundle.getBundle(FILENAME, sessionLocal);
        return BUNDLE.getString(key);
    }
}
