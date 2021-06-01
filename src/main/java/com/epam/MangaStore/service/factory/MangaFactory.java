package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.*;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.util.ImageManager;

import java.sql.SQLException;
import java.util.List;

public class MangaFactory {

    private static MangaFactory instance = new MangaFactory();

    private MangaStatusDAO mangaStatusDAO = new MangaStatusDAOImpl();
    private PublisherDAO publisherDAO = new PublisherDAOImpl();
    private AuthorDAO authorDAO = new AuthorDAOImpl();
    private GenreDAO genreDAO = new GenreDAOImpl();
    private CoverDAO coverDAO = new CoverDAOImpl();
    private MangaDAO mangaDAO = new MangaDAOImpl();

    private VolumeFactory volumeFactory = VolumeFactory.getInstance();

    private MangaFactory() {
    }

    public List<Manga> fillMangasToDisplayAll(Integer localID) throws SQLException {
        List<Manga> mangas = mangaDAO.selectAll();
        for (Manga manga : mangas) {
            manga.setStatus(mangaStatusDAO.selectMangaStatusByID(manga.getStatusID(), localID));
            manga.setPublisher(publisherDAO.selectPublisherByID(manga.getPublisherID()));
            byte[] coverBytes = coverDAO.selectCoverByID(manga.getCoverID());
            String cover = ImageManager.encodeByteToString(coverBytes);
            manga.setCover(cover);
        }
        return mangas;
    }

    public Manga fillMangaToDisplayOne(Long mangaID, Integer localID) throws SQLException {

        Manga manga = mangaDAO.selectMangaByID(mangaID);

        byte[] coverBytes = coverDAO.selectCoverByID(manga.getCoverID());
        String cover = ImageManager.encodeByteToString(coverBytes);

        manga.setAuthors(authorDAO.selectAllAuthorsByMangaID(mangaID));
        manga.setGenres(genreDAO.selectGenresByMangaLanguageID(mangaID, localID));
        manga.setVolumes(volumeFactory.fillVolumesToDisplayAll(mangaID));         //??name
        manga.setStatus(mangaStatusDAO.selectMangaStatusByID(manga.getStatusID(), localID));
        manga.setPublisher(publisherDAO.selectPublisherByID(manga.getPublisherID()));

        manga.setCover(cover);

        return manga;
    }

    public List<Manga> fillMangasByFilter(Long genreID, Integer localID) throws SQLException {

        List<Manga> mangas = mangaDAO.selectMangaByFilter(genreID, localID);
        for (Manga manga : mangas) {
            manga.setStatus(mangaStatusDAO.selectMangaStatusByID(manga.getStatusID(), localID));
            manga.setPublisher(publisherDAO.selectPublisherByID(manga.getPublisherID()));
            byte[] coverBytes = coverDAO.selectCoverByID(manga.getCoverID());
            String cover = ImageManager.encodeByteToString(coverBytes);
            manga.setCover(cover);
        }
        return mangas;
    }


    public static MangaFactory getInstance() {
        if (instance == null) {
            instance = new MangaFactory();
        }
        return instance;
    }
}
