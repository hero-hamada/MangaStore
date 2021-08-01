package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.GenreDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.GenreDAO;
import com.epam.MangaStore.entity.Genre;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.service.builder.MangaBuilder;
import com.epam.MangaStore.service.builder.VolumeBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
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

public class DisplayAllVolumesService implements Service {

    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();
    private RequestDispatcher dispatcher;
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(SIGN_IN_JSP);
            dispatcher.forward(request, response);
        }
        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
        List<Volume> volumes = volumeBuilder.fillAllToDisplay(mangaID);
        request.setAttribute(VOLUMES, volumes);

        serviceFactory.getService(PREPARE_VOLUMES_PAGE_SERVICE).execute(request, response);
    }
}
