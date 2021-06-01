package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.LanguageDAO;
import com.epam.MangaStore.database.dao.impl.LanguageDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class ChangeLocaleService implements Service {

    private LanguageDAO languageDAO = new LanguageDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;

        HttpSession session = request.getSession();
        String selectedLocale = request.getParameter(LOCALE);

        Integer languageID = languageDAO.selectIdByName(selectedLocale);

        session.setAttribute(LOCALE, selectedLocale);
        session.setAttribute(LOCALE_ID, languageID);

        dispatcher = request.getRequestDispatcher(INDEX_JSP);
        dispatcher.forward(request, response);
    }
}
