package com.epam.MangaStore.database.dao.interfaces;

import java.sql.SQLException;

public interface MangaToGenreDAO {

    Long insert(Long mangaID, Integer authorID) throws SQLException;

    boolean isPairExists(Long mangaID, Integer authorID) throws SQLException;

    void delete(Long mangaID, Integer authorID) throws SQLException;
}
