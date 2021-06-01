package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Volume;

import java.sql.SQLException;
import java.util.List;

public interface VolumeDAO {
    List<Volume> selectAllVolumesByMangaID(Long id) throws SQLException;

    Volume selectVolumeByID(Long volumeID) throws SQLException;

}
