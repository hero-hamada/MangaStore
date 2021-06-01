package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Volume;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolumeDAOImpl implements VolumeDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_VOLUMES_BY_MANGA_ID = "SELECT * FROM volume WHERE manga_id = ?";
    private static final String SELECT_VOLUME_BY_ID = "SELECT * FROM volume WHERE id = ?";

    public Volume getVolumeByResultSet(ResultSet resultSet) throws SQLException {

        Volume volume = new Volume();
        volume.setId(resultSet.getLong("id"));
        volume.setTitle(resultSet.getString("title"));
        volume.setIsbn(resultSet.getString("isbn"));
        volume.setPageCount(resultSet.getInt("page_count"));
        volume.setFormat(resultSet.getString("format"));
        volume.setSize(resultSet.getString("size"));
        volume.setBinding(resultSet.getString("binding"));
        volume.setPrice(resultSet.getLong("price"));
        volume.setReleaseDate(resultSet.getDate("release_date"));
        volume.setMangaID(resultSet.getLong("manga_id"));
        volume.setNumber(resultSet.getInt("number"));
        volume.setCoverID(resultSet.getLong("cover_id"));
        volume.setIsActive(resultSet.getBoolean("is_active"));

        return volume;
    }

    public List<Volume> selectAllVolumesByMangaID(Long mangaID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Volume> volumes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VOLUMES_BY_MANGA_ID)) {
            preparedStatement.setLong(1, mangaID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                volumes.add(getVolumeByResultSet(resultSet));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return volumes;
    }
    public Volume selectVolumeByID(Long volumeID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Volume volume = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOLUME_BY_ID)) {
            preparedStatement.setLong(1, volumeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                volume = getVolumeByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return volume;
    }
}
