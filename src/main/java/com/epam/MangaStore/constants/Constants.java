package com.epam.MangaStore.constants;

public class Constants {

    public final static Integer ROLE_USER_ID = 0;
    public final static Integer ROLE_ADMIN_ID = 1;
    public final static Integer USER_STATUS_ACTIVE_ID = 1;
    public final static Integer USER_STATUS_DELETED_ID = 2;
//    public final static Integer USER_BANNED = 1;
//    public final static Integer USER_UNBANNED = 0;
    public final static String LOCALE_ENGLISH = "en";
    public final static String LOCALE_RUSSIAN = "ru";
    public final static Integer LOCALE_ENGLISH_ID = 1;
    public final static Integer LOCALE_RUSSIAN_ID = 2;
    public final static Integer ORDER_STATUS_IN_PROCESSING = 1;

    public Integer getRoleUserID() {
        return ROLE_USER_ID;
    }

    public Integer getRoleAdminID() {
        return ROLE_ADMIN_ID;
    }

    public Integer getUserStatusActiveID() {
        return USER_STATUS_ACTIVE_ID;
    }

    public Integer getUserStatusDeletedID() {
        return USER_STATUS_DELETED_ID;
    }

    public static String getLocaleEnglish() {
        return LOCALE_ENGLISH;
    }

    public static String getLocaleRussian() {
        return LOCALE_RUSSIAN;
    }

    public static Integer getLocaleEnglishID() {
        return LOCALE_ENGLISH_ID;
    }

    public static Integer getLocaleRussianID() {
        return LOCALE_RUSSIAN_ID;
    }

    public final static String ERROR_SERVICE = "/error";
    public final static String REGISTER_SERVICE = "/register";
    public final static String SIGN_IN_SERVICE = "/signIn";
    public final static String SIGN_OUT_SERVICE = "/signOut";
    public final static String CHANGE_LOCALE_SERVICE = "/changeLocale";
    public final static String DISPLAY_ALL_USERS_SERVICE = "/DisplayAllUsers";
    public final static String EDIT_USER_ACCESS_SERVICE = "/EditUserAccess";
    public final static String DISPLAY_ALL_MANGAS_SERVICE = "/DisplayAllMangas";
    public final static String DISPLAY_ALL_VOLUMES_SERVICE = "/DisplayAllVolumes";
    public final static String ADD_TO_CART_SERVICE = "/AddToCart";
    public final static String DISPLAY_CART_SERVICE = "/DisplayCart";
    public final static String SET_QUANTITY_SERVICE = "/SetQuantity";
    public final static String DELETE_CART_ITEM_SERVICE = "/DeleteCartItem";
    public final static String CHECK_OUT_BY_CART_SERVICE = "/CheckOutByCart";
    public final static String CONFIRM_ORDER_SERVICE = "/ConfirmOrder";
    public final static String DISPLAY_MY_ORDERS_SERVICES = "/DisplayMyOrders";
    public final static String DISPLAY_ALL_ORDERS_SERVICES = "/DisplayAllOrders";
    public final static String EDIT_ORDER_STATUS_SERVICE = "/EditOrderStatus";
    public final static String FILTER_MANGA_SERVICE = "/FilterManga";
    public final static String FORWARD_SERVICE = "/Forward";

    public final static String LOCALE = "locale";
    public final static String LOCALE_ID = "localeID";

    public final static String USER = "user";
    public final static String USER_ID = "userID";
    public final static String USER_EMAIL = "userEmail";
    public final static String USER_LOGIN = "userLogin";
    public final static String USER_PASSWORD = "userPassword";
    public final static String CONFIRM_PASSWORD = "confirmPassword";
    public final static String USER_ROLE_ID = "userRoleID";
    public final static String USER_ADDRESS = "userAddress";
    public final static String USER_PHONE = "userPhone";
    public final static String USER_POSTAL_CODE = "userPostalCode";
    public final static String ALL_USERS = "allUsers";
    public final static String USER_STATUS_ID = "userStatusID";
    public final static String IS_USER_BANNED = "isUserBanned";
    public final static String USER_ORDERS = "userOrders";

    public final static String ALL_MANGAS = "allMangas";
    public final static String MANGA_ID = "mangaID";
    public final static String MANGA = "manga";

    public final static String VOLUME_ID = "volumeID";
    public final static String VOLUME_PRICE = "volumePrice";

    public final static String CART_ITEM_ID = "cartItemID";
    public final static String CART_TOTAL_PRICE = "cartTotalPrice";
    public final static String QUANTITY = "quantity";
    public final static String CART_ITEMS = "cartItems";

    public final static String ALL_ORDERS = "allOrders";
    public final static String ALL_ORDER_STATUSES = "allOrderStatuses";

    public final static String ORDER = "order";
    public final static String ORDER_ID = "orderID";
    public final static String ORDER_STATUS_ID = "orderStatusID";

    public final static String ALL_GENRES = "allGenres";
    public final static String ALL_MANGA_STATUSES = "allMangaStatuses";

    public final static Integer DEFAULT_VOLUME_QUANTITY = 1;
    public final static String DIRECTION = "direction";

    public final static String KEY_ERROR_NAME_FORMAT = "small.error.name.format";
    public final static String KEY_ERROR_EMAIL_FORMAT = "small.error.email.format";
    public final static String KEY_ERROR_PASSWORD_FORMAT = "small.error.password.format";
    public final static String KEY_ERROR_PASSWORD_CONFIRM = "small.error.password.confirm";
    public final static String KEY_ERROR_EMAIL_EXISTS = "small.error.email.exists";
    public final static String KEY_ERROR_LOGIN_EXISTS = "small.error.login.exists";
    public final static String KEY_ERROR_SIGN_IN = "small.error.sign.in";
    public final static String KEY_ERROR_ADDRESS_FORMAT = "small.error.address.format";
    public final static String KEY_ERROR_POSTAL_CODE_FORMAT = "small.error.postal.format";
    public final static String KEY_ERROR_PHONE_FORMAT = "small.error.phone.format";
    public final static String KEY_ERROR_PRICES_CHANGED = "small.error.order.price";


//    public final static String = ;

    public final static String REGISTER_JSP = "register.jsp";
    public final static String ERROR_JSP = "error.jsp";
    public final static String SIGN_IN_JSP = "signIn.jsp";
    public final static String INDEX_JSP = "index.jsp";
    public final static String USERS_JSP = "users.jsp";
    public final static String HEADER_JSP = "header.jsp";
    public final static String MANGAS_JSP = "mangas.jsp";
    public final static String VOLUMES_JSP = "volumes.jsp";
    public final static String CART_JSP = "cart.jsp";
    public final static String ORDER_JSP = "confirmOrder.jsp";
    public final static String MY_ORDERS_JSP = "myOrders.jsp";
    public final static String ORDERS_JSP = "orders.jsp";



    public final static String LOGIN_ERROR = "loginError";
    public final static String EMAIL_ERROR = "emailError";
    public final static String PASSWORD_ERROR = "passwordError";
    public final static String EMAIL_PASSWORD_ERROR = "emailPasswordError";
    public final static String ADDRESS_ERROR = "addressError";
    public final static String PHONE_ERROR = "phoneError";
    public final static String POSTAL_CODE_ERROR = "postalCodeError";
    public final static String ORDER_ERROR = "orderError";

}
