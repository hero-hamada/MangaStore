package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.AccessStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.AccessStatusDAO;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.service.builder.UserBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class EditUserAccessService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserBuilder userBuilder = UserBuilder.getInstance();
    private UserDAO userDAO = new UserDAOImpl();
    private AccessStatusDAO accessStatusDAO = new AccessStatusDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        if (userDAO.selectByID(Long.valueOf(request.getParameter(USER_ID))) != null &&
                accessStatusDAO.isAccessStatusExists(Integer.valueOf(request.getParameter(STATUS_ID)))) {
            userDAO.updateUserAccess(userBuilder.fillToUpdateAccess(request));
        }

        serviceFactory.getService(DISPLAY_ALL_USERS_SERVICE).execute(request, response);
    }
}
