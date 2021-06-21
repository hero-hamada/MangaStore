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
import static com.epam.MangaStore.util.validator.AccessValidator.isBannedOrDeleted;


public class SignInService implements Service {

    private final UserDAO userDAO = new UserDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        HttpSession session = request.getSession();

        String email = request.getParameter(USER_EMAIL).trim();
        String hashedPassword = DigestUtils.md5Hex(request.getParameter(USER_PASSWORD));

        User user = userDAO.selectUserByEmailPassword(email, hashedPassword);

        if (user == null) {
            request.setAttribute(EMAIL_PASSWORD_ERROR, ErrorMessageProvider.getErrorMessage(request, KEY_ERROR_SIGN_IN));
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        } else if (isBannedOrDeleted(user)) {
            request.setAttribute(USER_NOT_EXISTS_ERROR, ErrorMessageProvider.getErrorMessage(request, KEY_ERROR_USER_NOT_EXISTS));
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }

        session.setAttribute(USER, user);
        dispatcher = request.getRequestDispatcher(INDEX_JSP);
        dispatcher.forward(request, response);
    }
}

