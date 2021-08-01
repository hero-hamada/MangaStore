package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.constants.SortType;
import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.CoverDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Manga;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.util.ImageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.*;
import static com.epam.MangaStore.constants.Constants.ACCESS_STATUS_ID;

public class VolumeBuilder {

    private static VolumeBuilder instance = VolumeBuilder.getInstance();
    private final CoverDAO coverDAO = new CoverDAOImpl();
    private final VolumeDAO volumeDAO = new VolumeDAOImpl();

    private VolumeBuilder() {
    }

    public Volume fillNew(HttpServletRequest request) throws SQLException {
        Volume volume = new Volume();
        volume.setIsbn(request.getParameter(ISBN));
        volume.setTitle(request.getParameter(TITLE));
        volume.setPageCount(Integer.valueOf(request.getParameter(PAGE_COUNT)));
        volume.setFormat(request.getParameter(FORMAT));
        volume.setSize(request.getParameter(SIZE));
        volume.setBinding(request.getParameter(BINDING));
        volume.setPrice(Long.valueOf(request.getParameter(PRICE)));
        volume.setReleaseDate(Date.valueOf(request.getParameter(RELEASE_DATE)));
        volume.setMangaID(Long.valueOf(request.getParameter(MANGA_ID)));
        volume.setNumber(Integer.valueOf(request.getParameter(NUMBER)));
        volume.setCoverID(DEFAULT_COVER_ID);
        setCover(volume, DEFAULT_COVER_ID);
        volume.setAccessStatusID(ACCESS_STATUS_ACTIVE_ID);
        return volume;
    }

    public Volume fillOneToDisplay(Long volumeID) throws SQLException {
        Volume volume = volumeDAO.selectByID(volumeID);
        setCover(volume, volume.getCoverID());
        return volume;
    }

    public List<Volume> fillAllToDisplay(Long mangaID) throws SQLException {
        List<Volume> volumes = volumeDAO.selectAllVolumesByMangaID(mangaID);
        for (Volume volume : volumes) {
            setCover(volume, volume.getCoverID());
        }
        return volumes;
    }

    public Volume fillToUpdate(HttpServletRequest request) throws SQLException {
        Volume volume = fillNew(request);
        volume.setId(Long.valueOf(request.getParameter(VOLUME_ID)));
        volume.setCoverID(Long.valueOf(request.getParameter(COVER_ID)));
        setCover(volume, Long.valueOf(request.getParameter(COVER_ID)));
        volume.setAccessStatusID(Integer.valueOf(request.getParameter(ACCESS_STATUS_ID)));
        return volume;
    }

    public List<Volume> getActive(List<Volume> volumes) {
        List<Volume> activeVolumes = new ArrayList<>();
        for (Volume volume : volumes) {
            if (volume.getAccessStatusID().equals(ACCESS_STATUS_ACTIVE_ID))
                activeVolumes.add(volume);
        }
        return activeVolumes;
    }

    public void sortByType(List<Volume> volumes, SortType sortType) {
        switch (sortType) {
            case PRICE_ASC:
                volumes.sort(Comparator.comparing(Volume::getPrice));
                break;
            case PRICE_DESC:
                volumes.sort(Comparator.comparing(Volume::getPrice, Comparator.reverseOrder()));
                break;
            case NUMBER_ASC:
                volumes.sort(Comparator.comparing(Volume::getNumber));
                break;
            case NUMBER_DESC:
                volumes.sort(Comparator.comparing(Volume::getNumber, Comparator.reverseOrder()));
                break;
            case TITLE:
                volumes.sort(Comparator.comparing(Volume::getTitle));
                break;
        }
    }

    public void setCover(Volume volume, Long coverID) throws SQLException {
        byte[] coverBytes = coverDAO.selectCoverByID(coverID);
        volume.setCover(ImageManager.encodeByteToString(coverBytes));
    }

    public static VolumeBuilder getInstance() {
        if (instance == null) {
            instance = new VolumeBuilder();
        }
        return instance;
    }
}
