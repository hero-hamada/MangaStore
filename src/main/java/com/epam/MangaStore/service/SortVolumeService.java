package com.epam.MangaStore.service;

import com.epam.MangaStore.constants.SortType;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.service.builder.VolumeBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


import static com.epam.MangaStore.constants.Constants.*;


public class SortVolumeService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
        List<Volume> volumes = volumeBuilder.fillAllToDisplay(mangaID);

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            volumes = volumeBuilder.getActive(volumes);
        }

        if (request.getParameter(SORT_TYPE) != null) {
            volumeBuilder.sortByType(volumes, SortType.valueOf(request.getParameter(SORT_TYPE)));
            request.setAttribute(SELECTED_SORT_TYPE, request.getParameter(SORT_TYPE));
        }

        request.setAttribute(VOLUMES, volumes);
        serviceFactory.getService(PREPARE_VOLUMES_PAGE_SERVICE).execute(request, response);
    }
}
