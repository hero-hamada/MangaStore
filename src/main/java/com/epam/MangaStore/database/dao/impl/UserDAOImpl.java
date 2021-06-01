package com.epam.MangaStore.database.dao.impl;


import com.epam.MangaStore.database.connection.ConnectionPool;
import com.epam.MangaStore.database.dao.interfaces.UserDAO;
import com.epam.MangaStore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    private ConnectionPool connectionPool;
    private Connection connection;

    private static final String INSERT_USER = "INSERT INTO USER " +
            "(email, login, password, postal_code, address, phone, is_admin, is_banned, status_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM USER WHERE LOGIN = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID = ?";
    private static final String SELECT_USER_BY_EMAIL_PASSWORD = "SELECT * FROM USER WHERE (EMAIL = ? AND PASSWORD = ?)";
    private static final String SELECT_USER_ID_BY_EMAIL = "SELECT (ID) FROM USER WHERE (EMAIL = ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM USER";
    private static final String UPDATE_USER_ACCESS = "UPDATE USER SET  IS_BANNED = ?, STATUS_ID = ? WHERE ID = ?";
    private static final String UPDATE_USER_IS_BANNED = "UPDATE USER SET IS_BANNED = ? WHERE ID = ?";

    public User getUserByResultSet(ResultSet resultSet) throws SQLException{

        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setPostalCode(resultSet.getString("postal_code"));
        user.setAddress(resultSet.getString("address"));
        user.setPhone(resultSet.getString("phone"));
        user.setRoleID(resultSet.getInt("is_admin"));
        user.setBanned(resultSet.getBoolean("is_banned"));
        user.setStatusID(resultSet.getInt("status_id"));

        return user;
    }

    @Override
    public Long insert(User user) throws SQLException  {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Long generatedID = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPostalCode());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setInt(7, user.getRoleID());
            preparedStatement.setBoolean(8, user.isBanned());
            preparedStatement.setInt(9, user.getStatusID());
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

    @Override
    public boolean isUserExistsByEmail(String email) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExist;
    }

    @Override
    public User selectUserByEmailPassword(String email, String password) throws SQLException {

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    public boolean isUserExistsByLogin(String login) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        boolean isExist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
        } finally {
            connectionPool.returnConnection(connection);
        }
        return isExist;
    }


    public List<User> selectAllUsers() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<User> users = new ArrayList<>();
        User user;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
                users.add(user);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return users;
    }


// getting values from name attrs and updating the user in db by id????
    @Override
    public void updateUserAccess(Integer status, Boolean isBanned, Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ACCESS)) {
            preparedStatement.setBoolean(1, isBanned);
            preparedStatement.setInt(2, status);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
//updating in one time?
    @Override
    public User updateUserIsBanned(Boolean isBanned, Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_IS_BANNED)) {
            preparedStatement.setBoolean(1, isBanned);
            preparedStatement.setLong(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }

    @Override
    public User updateUserStatus(Integer status, Long userID) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public void updatePassword(Long id, String password) {

    }

    @Override
    public void updateEmail(Long id, String email) {

    }

    @Override
    public void updateUsername(Long id, String username) {

    }

    @Override
    public boolean isUniqueUsername() {
        return false;
    }

    @Override
    public Long selectUserIDByEmail(String email) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return id;
    }


    @Override
    public User selectUserByID(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
        return user;
    }
}
