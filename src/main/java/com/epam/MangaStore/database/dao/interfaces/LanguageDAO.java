package com.epam.MangaStore.database.dao.interfaces;

import java.sql.SQLException;

public interface LanguageDAO {
    Integer selectIdByName(String name) throws SQLException;
}
