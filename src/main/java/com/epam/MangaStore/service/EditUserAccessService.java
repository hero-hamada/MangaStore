package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class EditUserAccessService implements Service {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        Integer userStatusID = Integer.valueOf(request.getParameter(USER_STATUS_ID));
        Boolean isUserBanned = Boolean.parseBoolean(request.getParameter(IS_USER_BANNED));
        Long userID = Long.valueOf(request.getParameter(USER_ID));

        UserDAO userDAO = new UserDAOImpl();
        userDAO.updateUserAccess(userStatusID, isUserBanned, userID);
        serviceFactory.getService(DISPLAY_ALL_USERS_SERVICE).execute(request, response);
    }
}
