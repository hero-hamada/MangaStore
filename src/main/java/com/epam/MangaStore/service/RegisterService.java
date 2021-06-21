package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.builder.UserBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;
import static com.epam.MangaStore.util.ErrorMessageProvider.getErrorMessage;
import static com.epam.MangaStore.util.validator.UserValidator.*;


public class RegisterService implements Service {

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserBuilder userBuilder = UserBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {

        HttpSession session = request.getSession();
        User user = userBuilder.fillNew(request);

        if (!isLoginFormatCorrect(user.getLogin())) {
            displayErrorMessage(request, response, LOGIN_ERROR, KEY_ERROR_NAME_FORMAT);
        } else if (!isEmailFormatCorrect(user.getEmail())) {
            displayErrorMessage(request, response, EMAIL_ERROR, KEY_ERROR_EMAIL_FORMAT);
        } else if (!isPasswordFormatCorrect(request.getParameter(USER_PASSWORD))) {
            displayErrorMessage(request, response, PASSWORD_ERROR, KEY_ERROR_PASSWORD_FORMAT);
        } else if (!user.getPassword().equals(user.getConfirmPassword())) {
            displayErrorMessage(request, response, PASSWORD_ERROR, KEY_ERROR_PASSWORD_CONFIRM);
        } else if (!isPhoneFormatCorrect(user.getPhone())) {
            displayErrorMessage(request, response, PHONE_ERROR, KEY_ERROR_PHONE_FORMAT);
        } else if (!isPostalCodeFormatCorrect(user.getPostalCode())) {
            displayErrorMessage(request, response, POSTAL_CODE_ERROR, KEY_ERROR_POSTAL_CODE_FORMAT);
        } else if (userDAO.isUserExistsByEmail(user.getEmail())) {
            displayErrorMessage(request, response, EMAIL_ERROR, KEY_ERROR_EMAIL_EXISTS);
        } else if (userDAO.isUserExistsByLogin(user.getLogin())) {
            displayErrorMessage(request, response, LOGIN_ERROR, KEY_ERROR_LOGIN_EXISTS);
        } else {
            Long generatedID = userDAO.insert(user);
            user.setId(generatedID);
            session.setAttribute(USER, user);
            dispatcher = request.getRequestDispatcher(INDEX_JSP);
            dispatcher.forward(request, response);
        }
    }

    private void displayErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                     String errorName, String errorKey) throws ServletException, IOException {
        request.setAttribute(errorName, getErrorMessage(request, errorKey));
        dispatcher = request.getRequestDispatcher(REGISTER_JSP);
        dispatcher.forward(request, response);
    }
}
