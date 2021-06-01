package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.database.dao.impl.*;
import com.epam.MangaStore.database.dao.interfaces.CoverDAO;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Volume;
import com.epam.MangaStore.util.ImageManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolumeFactory {

    private static VolumeFactory instance = new VolumeFactory();

    private final CoverDAO coverDAO = new CoverDAOImpl();

    private final VolumeDAO volumeDAO = new VolumeDAOImpl();

    private VolumeFactory() {
    }

    public List<Volume> fillVolumesToDisplayAll(Long mangaID) throws SQLException {
        List<Volume> volumes = volumeDAO.selectAllVolumesByMangaID(mangaID);

        for (Volume volume : volumes) {
            byte[] coverBytes = coverDAO.selectCoverByID(volume.getCoverID());
            String cover = ImageManager.encodeByteToString(coverBytes);
            volume.setCover(cover);
        }
        return volumes;
    }

    public Volume fillVolume(Long volumeID) throws SQLException {

        Volume volume = volumeDAO.selectVolumeByID(volumeID);
        byte[] coverBytes = coverDAO.selectCoverByID(volume.getCoverID());
        String cover = ImageManager.encodeByteToString(coverBytes);
        volume.setCover(cover);
        return volume;
    }

    public boolean isVolumePricesSame(List<Long> volumesID, List<Long> requestPrices) throws SQLException {

        List<Long> currentPrices = new ArrayList<>();
        for (Long volumeID : volumesID) {
            currentPrices.add(volumeDAO.selectVolumeByID(volumeID).getPrice());
        }

        return currentPrices.equals(requestPrices);
    }

    public static VolumeFactory getInstance() {
        if (instance == null) {
            instance = new VolumeFactory();
        }
        return instance;
    }
}
