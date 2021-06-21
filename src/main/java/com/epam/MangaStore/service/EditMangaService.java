package com.epam.MangaStore.service;

import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.*;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.service.builder.MangaBuilder;
import com.epam.MangaStore.service.factory.ServiceFactory;
import com.epam.MangaStore.util.ImageManager;
import com.epam.MangaStore.util.validator.MangaValidator;
import com.epam.MangaStore.util.validator.AccessValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.MangaStore.constants.Constants.*;

public class EditMangaService implements Service {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private MangaBuilder mangaBuilder = MangaBuilder.getInstance();
    private MangaValidator mangaValidator = new MangaValidator();
    private MangaDAO mangaDAO = new MangaDAOImpl();
    private CoverDAO coverDAO = new CoverDAOImpl();
    private LanguageDAO languageDAO = new LanguageDAOImpl();
    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    private AccessStatusDAO accessStatusDAO = new AccessStatusDAOImpl();
    private ReleasingStatusDAO releasingStatusDAO = new ReleasingStatusDAOImpl();
    private RequestDispatcher dispatcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {

        HttpSession session = request.getSession();

        if (AccessValidator.isAccessDenied(ROLE_ADMIN_ID, session)) {
            dispatcher = request.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(request, response);
        }

//        Long mangaID = Long.valueOf(request.getParameter(MANGA_ID));
        Integer localID = (Integer) session.getAttribute(LOCALE_ID);
//        request.setAttribute(MANGA, mangaBuilder.fillOneToDisplay(mangaID, localID));

        if (mangaValidator.isEmptyParamExists(request)) {
            displayErrorMessage(request, response, EMPTY_FIELD_ERROR);
        } else if (languageDAO.isLanguageExists(Integer.valueOf(request.getParameter(LANGUAGE_ID)))) {
            displayErrorMessage(request, response, LANGUAGE_NOT_EXISTS_ERROR);
        } else if (publisherDAO.selectPublisherByName(request.getParameter(PUBLISHER_NAME)) == null) {
            displayErrorMessage(request, response, PUBLISHER_NOT_EXISTS_ERROR);
        } else if (releasingStatusDAO.selectByID(Integer.valueOf(request.getParameter(RELEASING_STATUS_ID)), localID) == null) {
            displayErrorMessage(request, response, RELEASING_STATUS_NOT_EXISTS_ERROR);
        } else if (!accessStatusDAO.isAccessStatusExists(Integer.valueOf(request.getParameter(ACCESS_STATUS_ID)))) {
            displayErrorMessage(request, response, ACCESS_STATUS_ERROR);
        } else {
            Manga manga = mangaBuilder.fillToUpdate(request);
            setNewCover(request, response, manga);
            mangaDAO.update(manga);
            serviceFactory.getService(PREPARE_EDIT_MANGA_PAGE_SERVICE).execute(request, response);
        }
    }

    private void displayErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                     String errorName) throws ServletException, IOException, SQLException, ParseException {
        request.setAttribute(errorName, ERROR_OCCURRED);
        serviceFactory.getService(PREPARE_EDIT_MANGA_PAGE_SERVICE).execute(request, response);
    }

    private void setNewCover(HttpServletRequest request, HttpServletResponse response, Manga manga) throws IOException, ServletException, SQLException, ParseException {
        Part part = request.getPart(COVER);
        if (part.getSize() > EMPTY_REQUEST_LENGTH) {
            if (!ImageManager.isImageFormatValid(part)) {
                displayErrorMessage(request, response, COVER_ERROR);
            }
            Long generatedCoverID = coverDAO.insert(part.getInputStream());
            manga.setCoverID(generatedCoverID);
        }
    }
}
