package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.VolumeDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.service.builder.VolumeBuilder;
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

public class PrepareEditVolumePageService implements Service {

    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VolumeDAO volumeDAO = new VolumeDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }
        Long volumeID = Long.valueOf(request.getParameter(VOLUME_ID));

        if (volumeDAO.selectByID(volumeID) == null) {
            serviceFactory.getService(SORT_VOLUMES_SERVICE).execute(request, response);
        } else {
            Volume volume = volumeBuilder.fillOneToDisplay(volumeID);
            request.setAttribute(VOLUME, volume);
            dispatcher = request.getRequestDispatcher(EDIT_VOLUME_JSP);
            dispatcher.forward(request, response);
        }
    }
}
