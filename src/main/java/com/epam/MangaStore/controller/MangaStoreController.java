package com.epam.MangaStore.controller;

import com.epam.MangaStore.service.Service;
import com.epam.MangaStore.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MangaStoreController extends HttpServlet {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();

        Service currentService = serviceFactory.getService(requestURI);
        try {
            currentService.execute(request, response);
        } catch (ParseException | SQLException | ServletException e) {
            LOGGER.error(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}