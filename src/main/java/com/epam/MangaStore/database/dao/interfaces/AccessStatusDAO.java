package com.epam.MangaStore.database.dao.interfaces;

import java.sql.SQLException;

public interface AccessStatusDAO {

    boolean isAccessStatusExists(Integer statusID) throws SQLException;
}
