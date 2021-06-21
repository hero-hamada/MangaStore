package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Publisher;

import java.sql.SQLException;
import java.util.List;

public interface PublisherDAO {

    Long insert(Publisher publisher) throws SQLException;

    Publisher selectByID(Long id) throws SQLException;

    Publisher selectPublisherByName(String name) throws SQLException;

    List<Publisher> selectAll() throws SQLException;

    void update(Publisher publisher) throws SQLException;
}
