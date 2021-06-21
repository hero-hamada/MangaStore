package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import static com.epam.MangaStore.constants.Constants.*;

public class UserBuilder {

    private static UserBuilder instance = new UserBuilder();

    private UserBuilder() {
    }

    public User fillNew(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(USER_EMAIL).trim());
        user.setLogin(request.getParameter(USER_LOGIN).trim());
        user.setPostalCode(request.getParameter(USER_POSTAL_CODE).trim());
        user.setAddress(request.getParameter(USER_ADDRESS).trim());
        user.setPhone(request.getParameter(USER_PHONE).trim());
        user.setRoleID(ROLE_USER_ID);
        user.setBanned(false);
        user.setStatusID(ACCESS_STATUS_ACTIVE_ID);
        user.setPassword(DigestUtils.md5Hex(request.getParameter(USER_PASSWORD)));
        user.setConfirmPassword(DigestUtils.md5Hex(request.getParameter(CONFIRM_PASSWORD)));
        return user;
    }

    public User fillToUpdate(HttpServletRequest request, User oldUser) {
        User newUser = new User();
        newUser.setId(oldUser.getId());
        newUser.setLogin(oldUser.getLogin());
        newUser.setRoleID(oldUser.getRoleID());
        newUser.setBanned(oldUser.isBanned());
        newUser.setStatusID(oldUser.getStatusID());
        newUser.setEmail(request.getParameter(USER_EMAIL).trim());
        newUser.setPostalCode(request.getParameter(USER_POSTAL_CODE).trim());
        newUser.setAddress(request.getParameter(USER_ADDRESS).trim());
        newUser.setPhone(request.getParameter(USER_PHONE).trim());
        newUser.setPassword(DigestUtils.md5Hex(request.getParameter(USER_PASSWORD)));
        newUser.setOldPassword(DigestUtils.md5Hex(request.getParameter(OLD_PASSWORD)));
        return newUser;
    }

    public static UserBuilder getInstance() {
        if (instance == null) {
            instance = new UserBuilder();
        }
        return instance;
    }
}
