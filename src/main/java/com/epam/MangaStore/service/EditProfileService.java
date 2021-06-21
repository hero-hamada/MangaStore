package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.builder.UserBuilder;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;
import static com.epam.MangaStore.util.validator.UserValidator.*;


public class EditProfileService implements Service {

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserBuilder userBuilder = UserBuilder.getInstance();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {

        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute(USER);

        if (AccessValidator.isBannedOrDeleted(oldUser)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        User newUser = userBuilder.fillToUpdate(request, oldUser);

        if (!isEmailFormatCorrect(newUser.getEmail())) {
            displayErrorMessage(request, response, EMAIL_FORMAT_ERROR);
        } else if (!isPasswordFormatCorrect(request.getParameter(USER_PASSWORD))) {
            displayErrorMessage(request, response, PASSWORD_FORMAT_ERROR);
        } else if (!newUser.getOldPassword().equals(oldUser.getPassword())) {
            displayErrorMessage(request, response, NOT_OLD_PASSWORD_ERROR);
        } else if (!isPhoneFormatCorrect(newUser.getPhone())) {
            displayErrorMessage(request, response, PHONE_ERROR);
        } else if (!isPostalCodeFormatCorrect(newUser.getPostalCode())) {
            displayErrorMessage(request, response, POSTAL_CODE_ERROR);
        } else if (!newUser.getEmail().equals(oldUser.getEmail()) && userDAO.isUserExistsByEmail(newUser.getPostalCode())) {
            displayErrorMessage(request, response, EMAIL_EXISTS_ERROR);
        } else {
            userDAO.update(newUser);
            session.setAttribute(USER, newUser);
            dispatcher = request.getRequestDispatcher(EDIT_PROFILE_JSP);
            dispatcher.forward(request, response);
        }
    }

    private void displayErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                     String errorName) throws ServletException, IOException {
        request.setAttribute(errorName, ERROR_OCCURRED);
        dispatcher = request.getRequestDispatcher(EDIT_PROFILE_JSP);
        dispatcher.forward(request, response);
    }
}
