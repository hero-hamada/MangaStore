package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.UserFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.MangaStore.constants.Constants.*;


public class RegisterUserService implements Service {

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserFactory userFactory = UserFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  SQLException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        Boolean isAllOkWithEmail = false;             //just for example
        Boolean isAnotherParameterChecking = false;   //just for example

        String email = request.getParameter(USER_EMAIL).trim();
        String login = request.getParameter(USER_LOGIN).trim();
        String password = request.getParameter(USER_PASSWORD).trim();

//        here I am validating request parameters
        if (!isAllOkWithEmail) {
//               displaying errors
        } else if (!isAnotherParameterChecking) {
//               checking and displaying errors
        } else {
            /// filling new registered User with UserFactory for Single Responsibility
            User user = userFactory.fillNewUser(request);
            session.setAttribute(USER, user);
            dispatcher = request.getRequestDispatcher(INDEX_JSP);
            dispatcher.forward(request, response);
        }
    }
}
