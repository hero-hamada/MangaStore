package com.epam.MangaStore.database.dao.interfaces;

import java.sql.SQLException;

public interface MangaToAuthorDAO {

    Long insert(Long mangaID, Long authorID) throws SQLException;

    boolean isPairExists(Long mangaID, Long authorID) throws SQLException;

    void delete(Long mangaID, Long authorID) throws SQLException;
}
