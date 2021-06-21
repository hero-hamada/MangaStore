package com.epam.MangaStore.util.validator;

import com.epam.MangaStore.entity.User;

import javax.servlet.http.HttpSession;

import static com.epam.MangaStore.constants.Constants.*;

public class AccessValidator {

    private AccessValidator() {
        throw new UnsupportedOperationException();
    }

    public static boolean isAccessDenied(Integer acceptedRoleID, HttpSession session) {
        User user = (User) session.getAttribute(USER);
        if (user != null) {
            return isBannedOrDeleted(user) || !acceptedRoleID.equals(user.getRoleID());
        }
        return true;
    }

    public static boolean isBannedOrDeleted(User user) {
        return user.isBanned() || user.getStatusID().equals(ACCESS_STATUS_DELETED_ID);
    }
}
