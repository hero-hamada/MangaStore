package com.epam.MangaStore.database.dao.interfaces;


import com.epam.MangaStore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    Long insert(User user) throws SQLException;

    void updateUserAccess(Integer status, Boolean isBanned, Long id) throws SQLException;

    User selectUserByEmailPassword(String email, String password) throws SQLException;

    void update(User user) throws SQLException;

    User selectByID(Long id) throws SQLException;

    boolean isUserExistsByEmail(String email) throws SQLException;

    boolean isUserExistsByLogin(String login) throws SQLException;

    List<User> selectAll() throws SQLException;
}
