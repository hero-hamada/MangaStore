package com.epam.MangaStore.database.dao.interfaces;

import com.epam.MangaStore.entity.Publisher;

import java.sql.SQLException;

public interface PublisherDAO {
    Publisher selectPublisherByID(Long id) throws SQLException;
}
