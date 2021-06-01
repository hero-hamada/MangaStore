package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.util.ErrorMessageProvider;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.MangaStore.constants.Constants.*;


public class SignInService implements Service {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        HttpSession session = request.getSession();

//        session.setAttribute(LOCALE, LOCALE_ENGLISH);
        String email = request.getParameter(USER_EMAIL).trim();
        String password = request.getParameter(USER_PASSWORD).trim();
        String hashedPassword = DigestUtils.md5Hex(password);
        String errorMessage;

        RequestDispatcher dispatcher;
        User user = userDAO.selectUserByEmailPassword(email, hashedPassword);
        if (user != null) {
            session.setAttribute(USER, user);
            dispatcher = request.getRequestDispatcher(INDEX_JSP);
            dispatcher.forward(request,response);
        } else {
            errorMessage = ErrorMessageProvider.get(request, KEY_ERROR_SIGN_IN);
            request.setAttribute(EMAIL_PASSWORD_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request,response);
        }
    }
}

