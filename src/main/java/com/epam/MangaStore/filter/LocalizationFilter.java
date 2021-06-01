package com.epam.MangaStore.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.epam.MangaStore.constants.Constants.*;


public class LocalizationFilter implements Filter {

    private String locale;
    private Integer localeID;
    private static String CONFIG_LOCALE = "en";
    private static Integer CONFIG_LOCALE_ID = 1;
    private static String INIT_PARAM_LOCALE = "local";
    private static String INIT_PARAM_LOCALE_ID = "localID";

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
        String sessionLocale = (String) session.getAttribute("locale");
        Integer sessionLocaleID = (Integer) session.getAttribute("localeID");
        if (sessionLocale == null || sessionLocaleID == null) {
            session.setAttribute("locale", locale);
            session.setAttribute("localeID", localeID);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
