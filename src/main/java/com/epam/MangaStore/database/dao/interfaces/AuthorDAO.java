package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDAO {

    Long insert(Author author) throws SQLException;

    void update(Author author) throws SQLException;

    Author selectByID(Long authorID) throws SQLException;

    Long selectIdByFullName(Author author) throws SQLException;

    List<Author> selectAllAuthorsByMangaID(Long id) throws SQLException;

    List<Author> selectAll() throws SQLException;
}
