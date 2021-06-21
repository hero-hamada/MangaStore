package com.epam.MangaStore.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.epam.MangaStore.constants.Constants.*;

import static com.epam.MangaStore.constants.Constants.LOCALE;
import static com.epam.MangaStore.constants.Constants.LOCALE_ID;


public class LocalizationFilter implements Filter {

    private String locale;
    private Integer localeID;
    private static String CONFIG_LOCALE = "en";
    private static Integer CONFIG_LOCALE_ID = 1;
    private static String INIT_PARAM_LOCALE = "locale";
    private static String INIT_PARAM_LOCALE_ID = "localeID";

    @Override
    public void init(FilterConfig filterConfig) {
        locale = filterConfig.getInitParameter(INIT_PARAM_LOCALE);
        localeID = Integer.parseInt(filterConfig.getInitParameter(INIT_PARAM_LOCALE_ID));
        if (null == locale || null == localeID) {
            locale = CONFIG_LOCALE;
            localeID = CONFIG_LOCALE_ID;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == locale || null == localeID) {
            locale = CONFIG_LOCALE;
            localeID = CONFIG_LOCALE_ID;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        String sessionLocale = (String) session.getAttribute(LOCALE);
        Integer sessionLocaleID = (Integer) session.getAttribute(LOCALE_ID);
        if (sessionLocale == null || sessionLocaleID == null) {
            session.setAttribute(LOCALE, locale);
            session.setAttribute(LOCALE_ID, localeID);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
