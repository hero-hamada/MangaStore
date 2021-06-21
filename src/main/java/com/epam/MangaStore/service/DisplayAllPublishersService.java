package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.PublisherDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.PublisherDAO;
import com.epam.MangaStore.entity.Publisher;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class DisplayAllPublishersService implements Service {

    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        List<Publisher> publishers = publisherDAO.selectAll();
        request.setAttribute(PUBLISHERS, publishers);
        dispatcher = request.getRequestDispatcher(EDIT_PUBLISHERS_JSP);
        dispatcher.forward(request, response);
    }
}
