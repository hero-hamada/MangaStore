package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.MangaStatus;

import java.sql.SQLException;
import java.util.List;

public interface MangaStatusDAO {
    MangaStatus selectMangaStatusByID(Integer statusID, Integer sessionLanguageID) throws SQLException;
    List<MangaStatus> selectAll(Integer sessionLanguageID) throws SQLException;
}
