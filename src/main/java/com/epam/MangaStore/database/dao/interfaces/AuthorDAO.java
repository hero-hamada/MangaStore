package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDAO {
    List<Author> selectAllAuthorsByMangaID(Long id) throws SQLException;
}
