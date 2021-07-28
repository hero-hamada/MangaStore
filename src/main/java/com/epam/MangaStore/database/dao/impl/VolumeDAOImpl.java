package com.epam.MangaStore.database.dao.impl;

import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.VolumeDAO;
import com.epam.MangaStore.entity.Volume;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.MangaStore.constants.Constants.ACCESS_STATUS_ACTIVE_ID;

public class VolumeDAOImpl implements VolumeDAO {


    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String SELECT_ALL_VOLUMES_BY_MANGA_ID = "SELECT * FROM volume WHERE manga_id=?";
    private static final String SELECT_ACTIVE_VOLUMES_BY_MANGA_ID =
            "SELECT * FROM volume WHERE manga_id=? AND access_status_id=" + ACCESS_STATUS_ACTIVE_ID;
    private static final String SELECT_VOLUME_BY_ISBN = "SELECT * FROM volume WHERE isbn = ?";
    private static final String SELECT_VOLUME_BY_ID = "SELECT * FROM volume WHERE id=?";
    private static final String UPDATE_VOLUME = "UPDATE volume SET title=?,isbn=?,page_count=?,format=?,size=?,binding=?," +
            "price=?,release_date=?,number=?,cover_id=?,access_status_id=? WHERE id=?";
    private static final String SELECT_VOLUME_BY_MANGA_ID_NUMBER = "SELECT * FROM volume WHERE manga_id=? AND number=?";
    private static final String INSERT_VOLUME =
            "INSERT INTO volume " +
            "(title, isbn, page_count, format, size, binding, price, release_date, manga_id, number, cover_id, access_status_id)" +
            "VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?)";

    private Volume getVolumeByResultSet(ResultSet resultSet) throws SQLException {

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
        volume.setAccessStatusID(resultSet.getInt("access_status_id"));
        return volume;
    }

    @Override
    public Long insert(Volume volume) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long generatedID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VOLUME, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, volume.getTitle());
            preparedStatement.setString(2, volume.getIsbn());
            preparedStatement.setInt(3, volume.getPageCount());
            preparedStatement.setString(4, volume.getFormat());
            preparedStatement.setString(5, volume.getSize());
            preparedStatement.setString(6, volume.getBinding());
            preparedStatement.setLong(7, volume.getPrice());
            preparedStatement.setDate(8, volume.getReleaseDate());
            preparedStatement.setLong(9, volume.getMangaID());
            preparedStatement.setInt(10, volume.getNumber());
            preparedStatement.setLong(11, volume.getCoverID());
            preparedStatement.setInt(12, volume.getAccessStatusID());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getLong(1);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return generatedID;
    }

    public Volume selectByID(Long volumeID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Volume volume = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOLUME_BY_ID)) {
            preparedStatement.setLong(1, volumeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                volume = getVolumeByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
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

    @Override
    public void update(Volume volume) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VOLUME)) {
            preparedStatement.setString(1, volume.getTitle());
            preparedStatement.setString(2, volume.getIsbn());
            preparedStatement.setInt(3, volume.getPageCount());
            preparedStatement.setString(4, volume.getFormat());
            preparedStatement.setString(5, volume.getSize());
            preparedStatement.setString(6, volume.getBinding());
            preparedStatement.setLong(7, volume.getPrice());
            preparedStatement.setDate(8, volume.getReleaseDate());
            preparedStatement.setInt(9, volume.getNumber());
            preparedStatement.setLong(10, volume.getCoverID());
            preparedStatement.setInt(11, volume.getAccessStatusID());
            preparedStatement.setLong(12, volume.getId());
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean isISBNExists(String isbn) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExists;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOLUME_BY_ISBN)) {
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExists = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExists;
    }

    @Override
    public boolean isMangaIDAndNumberExists(Long mangaID, Integer number) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExists;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOLUME_BY_MANGA_ID_NUMBER)) {
            preparedStatement.setLong(1, mangaID);
            preparedStatement.setInt(2, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExists = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExists;
    }

    @Override
    public List<Volume> selectActiveVolumesByMangaID(Long mangaID) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Volume> volumes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACTIVE_VOLUMES_BY_MANGA_ID)) {
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
}
