package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.database.dao.impl.UserDAOImpl;
import com.epam.MangaStore.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class DisplayAllUsersService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.selectAllUsers();
        request.setAttribute(ALL_USERS, users);
        RequestDispatcher dispatcher = request.getRequestDispatcher(USERS_JSP);
        dispatcher.forward(request, response);
    }
}
