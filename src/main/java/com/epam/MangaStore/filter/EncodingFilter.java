package com.epam.MangaStore.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding;
    private String contentType;
    private static String CONFIG_ENCODING = "UTF-8";
    private static String CONFIG_CONTENT_TYPE = "text/html; charset=UTF-8";
    private static String INIT_PARAM_ENCODING = "encoding";
    private static String INIT_PARAM_CONTENT_TYPE = "contentType";

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(INIT_PARAM_ENCODING);
        contentType = filterConfig.getInitParameter(INIT_PARAM_CONTENT_TYPE);
        if (null == encoding || null == contentType) {
            encoding = CONFIG_ENCODING;
            contentType = CONFIG_CONTENT_TYPE;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (null == encoding || null == contentType) {
            encoding = CONFIG_ENCODING;
            contentType = CONFIG_CONTENT_TYPE;
        }
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        servletResponse.setContentType(contentType);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
