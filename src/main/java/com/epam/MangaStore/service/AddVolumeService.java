package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.CoverDAOImpl;
import com.epam.MangaStore.database.dao.impl.VolumeDAOImpl;
import com.epam.MangaStore.database.dao.interfaces.CoverDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.service.builder.VolumeBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.ImageManager;
import com.epam.MangaStore.util.validator.AccessValidator;
import com.epam.MangaStore.util.validator.VolumeValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class AddVolumeService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private VolumeBuilder volumeBuilder = VolumeBuilder.getInstance();
    private VolumeValidator volumeValidator = new VolumeValidator();
    private VolumeDAO volumeDAO = new VolumeDAOImpl();
    private CoverDAO coverDAO = new CoverDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, request.getSession())) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        } else if (volumeValidator.isEmptyParamExists(request)) {
            displayErrorMessage(request, response, EMPTY_FIELD_ERROR);
        }

        Volume volume = volumeBuilder.fillNew(request);

        if (volumeValidator.isISBNInvalid(volume.getIsbn())) {
            displayErrorMessage(request, response, ISBN_ERROR);
        } else if (volumeValidator.isFormatInvalid(volume.getFormat())) {
            displayErrorMessage(request, response, FORMAT_ERROR);
        } else if (volumeValidator.isSizeFormatInvalid(volume.getSize())) {
            displayErrorMessage(request, response, SIZE_ERROR);
        } else if (volumeValidator.isNumberInvalid(volume.getMangaID(), volume.getNumber())) {
            displayErrorMessage(request, response, NUMBER_ERROR);
        } else if (volumeValidator.isPositiveParamsInvalid(volume)) {
            displayErrorMessage(request, response, NEGATIVE_VALUE_ERROR);
        } else {
            setNewCover(request, response, volume);
            volume.setId(volumeDAO.insert(volume));
            request.setAttribute(VOLUME, volume);
            dispatcher = request.getRequestDispatcher(EDIT_VOLUME_JSP);
            dispatcher.forward(request, response);
        }
    }

    private void displayErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                     String errorName) throws ServletException, IOException, SQLException, ParseException {
        request.setAttribute(MANGA_ID, Long.valueOf(request.getParameter(MANGA_ID)));
        request.setAttribute(errorName, ERROR_OCCURRED);
        serviceFactory.getService(PREPARE_ADD_VOLUME_PAGE_SERVICE).execute(request, response);
    }

    private void setNewCover(HttpServletRequest request, HttpServletResponse response, Volume volume) throws SQLException, ParseException, ServletException, IOException {
        if (request.getPart(COVER).getSize() > EMPTY_REQUEST_LENGTH) {
            if (!ImageManager.isImageFormatValid(request.getPart(COVER))) {
                displayErrorMessage(request, response, COVER_ERROR);
            }
            Long generatedCoverID = coverDAO.insert(request.getPart(COVER).getInputStream());
            volume.setCoverID(generatedCoverID);
            volumeBuilder.setCover(volume, generatedCoverID);
        }
    }
}
