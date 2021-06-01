package com.epam.MangaStore.database.dao.interfaces;

import java.sql.SQLException;

public interface CoverDAO {

    byte[] selectCoverByID(Long id) throws SQLException;
}
