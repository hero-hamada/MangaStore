package com.epam.MangaStore.database.dao.interfaces;

import java.io.InputStream;
import java.sql.SQLException;

public interface CoverDAO {

    byte[] selectCoverByID(Long id) throws SQLException;

    Long insert(InputStream uploadingCover) throws SQLException;
}
