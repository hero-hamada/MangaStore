package com.epam.MangaStore.database.dao.interfaces;


import com.epam.MangaStore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    Long insert(User user) throws SQLException;

    User updateUserStatus(Integer status, Long userID);

    void updateUserAccess(Integer status, Boolean isBanned, Long id) throws SQLException;

    User updateUserIsBanned(Boolean isBanned, Long id) throws SQLException;

    void delete(Long id);
    void updatePassword(Long id, String password);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);
    boolean isUniqueUsername();
    Long selectUserIDByEmail(String email) throws SQLException;
    User selectUserByEmailPassword(String email, String password) throws SQLException;
    User selectUserByID(Long id) throws SQLException;
    boolean isUserExistsByEmail(String email) throws SQLException;
    boolean isUserExistsByLogin(String login) throws SQLException;
    List<User> selectAllUsers() throws SQLException;
}
