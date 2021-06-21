package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Manga;

import java.sql.SQLException;
import java.util.List;

public interface MangaDAO {

    List<Manga> selectAll() throws SQLException;

    Manga selectByID(Long mangaID) throws SQLException;

    List<Manga> selectAllByFilter(Integer genreID, Integer localID) throws SQLException;

    boolean isMangaDeleted(Long id) throws SQLException;

    Long insert(Manga manga) throws SQLException;

    void update(Manga manga) throws SQLException;

    List<Manga> selectByTitle(String title) throws SQLException;
}
