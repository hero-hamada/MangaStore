package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import static com.epam.MangaStore.constants.Constants.*;

public class UserFactory {

    private static UserFactory instance = new UserFactory();

    private UserFactory() {
    }

    public User fillNewUser(HttpServletRequest request) {
        User user = new User();
        // Writing here same code and violating DRY????
        user.setEmail(request.getParameter(USER_EMAIL).trim());
        user.setLogin(request.getParameter(USER_LOGIN).trim());
        user.setPostalCode(request.getParameter(USER_POSTAL_CODE).trim());
        user.setAddress(request.getParameter(USER_ADDRESS).trim());
        user.setPhone(request.getParameter(USER_PHONE).trim());
        user.setRoleID(ROLE_USER_ID);
        /// is false should be constant var NOT_BANNED = false??
        user.setBanned(false);
        user.setStatusID(USER_STATUS_ACTIVE_ID);

        String hashedPassword = DigestUtils.md5Hex(request.getParameter(USER_PASSWORD).trim());
        user.setPassword(hashedPassword);

        return user;
    }

    public static UserFactory getInstance() {
        return instance;
    }
}
