package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.entity.User;
import com.epam.MangaStore.service.factory.UserFactory;
import com.epam.MangaStore.util.ErrorMessageProvider;
import com.epam.MangaStore.util.InputFormatManager;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.MangaStore.constants.Constants.*;


public class RegisterService implements Service {

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserFactory userFactory = UserFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  SQLException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
//        session.setAttribute(LOCALE, LOCALE_ENGLISH);

        String email = request.getParameter(USER_EMAIL).trim();
        String login = request.getParameter(USER_LOGIN).trim();
        String password = request.getParameter(USER_PASSWORD).trim();
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD).trim();
        // should I validate address???
        String address = request.getParameter(USER_ADDRESS).trim();
        String phone = request.getParameter(USER_PHONE).trim();
        String postalCode = request.getParameter(USER_POSTAL_CODE).trim();

        String hashedPassword = DigestUtils.md5Hex(password);
        String hashedConfirmPassword = DigestUtils.md5Hex(confirmPassword);
        String errorMessage;

        if (!InputFormatManager.isLoginFormatCorrect(login)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_NAME_FORMAT);
            request.setAttribute(LOGIN_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (!InputFormatManager.isEmailFormatCorrect(email)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_EMAIL_FORMAT);
            request.setAttribute(EMAIL_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (!InputFormatManager.isPasswordFormatCorrect(password)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_PASSWORD_FORMAT);
            request.setAttribute(PASSWORD_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (!hashedPassword.equals(hashedConfirmPassword)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_PASSWORD_CONFIRM);
            request.setAttribute(PASSWORD_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (!InputFormatManager.isPhoneFormatCorrect(phone)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_PHONE_FORMAT);
            request.setAttribute(PHONE_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (!InputFormatManager.isPostalCodeFormatCorrect(postalCode)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_POSTAL_CODE_FORMAT);
            request.setAttribute(POSTAL_CODE_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (userDAO.isUserExistsByEmail(email)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_EMAIL_EXISTS);
            request.setAttribute(EMAIL_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else if (userDAO.isUserExistsByLogin(login)) {
            errorMessage = ErrorMessageProvider.get(request,KEY_ERROR_LOGIN_EXISTS);
            request.setAttribute(LOGIN_ERROR, errorMessage);
            dispatcher = request.getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(request, response);
        } else {

            User user = userFactory.fillNewUser(request);
            Long generatedID = userDAO.insert(user);
            if (generatedID != null) {
                user.setId(generatedID);
                session.setAttribute(USER, user);
                dispatcher = request.getRequestDispatcher(INDEX_JSP);
                dispatcher.forward(request, response);
            } else {
                dispatcher = request.getRequestDispatcher(REGISTER_JSP);
                dispatcher.forward(request, response);
            }
        }
    }
}
