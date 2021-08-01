package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Volume;

import java.sql.SQLException;
import java.util.List;

public interface VolumeDAO {

    Long insert(Volume volume) throws SQLException;

    List<Volume> selectAllVolumesByMangaID(Long id) throws SQLException;

    Volume selectByID(Long volumeID) throws SQLException;

    void update(Volume volume) throws SQLException;

    boolean isISBNExists(String isbn) throws SQLException;

    boolean isMangaIDAndNumberExists(Long mangaID, Integer number) throws SQLException;
}
