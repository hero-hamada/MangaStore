package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.*;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.entity.Publisher;
import com.epam.MangaStore.util.ImageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;

public class MangaBuilder {

    private static MangaBuilder instance = new MangaBuilder();

    private ReleasingStatusDAO releasingStatusDAO = new ReleasingStatusDAOImpl();
    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    private AuthorDAO authorDAO = new AuthorDAOImpl();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private CoverDAO coverDAO = new CoverDAOImpl();
    private MangaDAO mangaDAO = new MangaDAOImpl();

    private MangaBuilder() {
    }

    public Manga fillNew(HttpServletRequest request) throws SQLException {
        Manga manga = new Manga();
        manga.setTitle(request.getParameter(TITLE));
        manga.setDescription(request.getParameter(DESCRIPTION));
        manga.setReleaseDate(Date.valueOf(request.getParameter(RELEASE_DATE)));
        manga.setReleaseStatusID(Integer.valueOf(request.getParameter(RELEASING_STATUS_ID)));
        manga.setLanguageID(Integer.valueOf(request.getParameter(LANGUAGE_ID)));
        manga.setAccessStatusID(ACCESS_STATUS_ACTIVE_ID);
        manga.setCoverID(DEFAULT_COVER_ID);
        setCover(manga, DEFAULT_COVER_ID);
        Publisher publisher = publisherDAO.selectPublisherByName(request.getParameter(PUBLISHER_NAME));
        manga.setPublisherID(publisher.getId());
        manga.setPublisher(publisher);
        return manga;
    }

    public Manga fillOneToDisplay(Long mangaID, Integer localID) throws SQLException {

        Manga manga = mangaDAO.selectByID(mangaID);
        manga.setAuthors(authorDAO.selectAllAuthorsByMangaID(mangaID));
        manga.setGenres(genreDAO.selectGenresByMangaLanguageID(mangaID, localID));
        manga.setStatus(releasingStatusDAO.selectByID(manga.getReleaseStatusID(), localID));
        manga.setPublisher(publisherDAO.selectByID(manga.getPublisherID()));
        setCover(manga, manga.getCoverID());
        return manga;
    }

    public List<Manga> fillAllToDisplay(Integer localID) throws SQLException {
        List<Manga> mangas = mangaDAO.selectAll();
        fillGiven(mangas, localID);
        return mangas;
    }

    public List<Manga> fillAllBySearch(Integer localeID, String title) throws SQLException {
        List<Manga> mangas = mangaDAO.selectByTitle(title);
        fillGiven(mangas, localeID);
        return mangas;
    }

    public List<Manga> fillByFilter(Integer genreID, Integer localID) throws SQLException {
        List<Manga> mangas = mangaDAO.selectAllByFilter(genreID, localID);
        fillGiven(mangas, localID);
        return mangas;
    }

    public Manga fillToUpdate(HttpServletRequest request) throws SQLException {
        Manga manga = fillNew(request);
        manga.setId(Long.valueOf(request.getParameter(MANGA_ID)));
        manga.setCoverID(Long.valueOf(request.getParameter(COVER_ID)));
        setCover(manga, Long.valueOf(request.getParameter(COVER_ID)));
        manga.setAccessStatusID(Integer.valueOf(request.getParameter(ACCESS_STATUS_ID)));
        return manga;
    }

    public List<Manga> getActive(List<Manga> mangas) {
        List<Manga> activeMangas = new ArrayList<>();
        for (Manga manga : mangas) {
            if (manga.getAccessStatusID().equals(ACCESS_STATUS_ACTIVE_ID))
                activeMangas.add(manga);
        }
        return activeMangas;
    }

    private void setCover(Manga manga, Long coverID) throws SQLException {
        byte[] coverBytes = coverDAO.selectCoverByID(coverID);
        manga.setCover(ImageManager.encodeByteToString(coverBytes));
    }

    private void fillGiven(List<Manga> mangas, Integer localID) throws SQLException {
        for (Manga manga : mangas) {
            manga.setStatus(releasingStatusDAO.selectByID(manga.getReleaseStatusID(), localID));
            setCover(manga, manga.getCoverID());
        }
    }

    public static MangaBuilder getInstance() {
        if (instance == null) {
            instance = new MangaBuilder();
        }
        return instance;
    }
}
