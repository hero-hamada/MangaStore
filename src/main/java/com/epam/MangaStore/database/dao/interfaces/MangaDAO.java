package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Manga;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MangaDAO {

    Manga getMangaByResultSet(ResultSet resultSet) throws SQLException;
    List<Manga> selectAll() throws SQLException;
    Manga selectMangaByID(Long mangaID) throws SQLException;
    List<Manga> selectMangaByFilter(Long genreID, Integer localID) throws SQLException;
}
