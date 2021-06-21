package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.ReleasingStatusDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.ReleasingStatusDAO;
import com.epam.MangaStore.entity.ReleasingStatus;
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

public class PrepareAddMangaPageService implements Service {

    private ReleasingStatusDAO releasingStatusDAO = new ReleasingStatusDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();
        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

        Integer localID = (Integer) session.getAttribute(LOCALE_ID);
        List<ReleasingStatus> releasingStatuses = releasingStatusDAO.selectAll(localID);
        request.setAttribute(RELEASING_STATUSES, releasingStatuses);

        RequestDispatcher dispatcher = request.getRequestDispatcher(ADD_MANGA_JSP);
        dispatcher.forward(request, response);
    }
}
