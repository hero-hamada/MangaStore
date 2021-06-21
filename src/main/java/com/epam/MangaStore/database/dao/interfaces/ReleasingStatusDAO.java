package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.ReleasingStatus;

import java.sql.SQLException;
import java.util.List;

public interface ReleasingStatusDAO {

    ReleasingStatus selectByID(Integer statusID, Integer sessionLanguageID) throws SQLException;

    List<ReleasingStatus> selectAll(Integer sessionLanguageID) throws SQLException;
}
