package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {
    List<Genre> selectGenresByMangaLanguageID(Long mangaID, Integer sessionLanguageID) throws SQLException;
    List<Genre> selectAll(Integer sessionLanguageID)  throws SQLException;
}
