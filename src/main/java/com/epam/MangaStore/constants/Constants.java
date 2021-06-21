package com.epam.MangaStore.constants;

public class Constants {

    public final static Integer ROLE_USER_ID = 0;
    public final static Integer ROLE_ADMIN_ID = 1;
    public final static Integer ACCESS_STATUS_ACTIVE_ID = 1;
    public final static Integer ACCESS_STATUS_DELETED_ID = 2;
    public final static Integer LOCALE_ENGLISH_ID = 1;
    public final static Integer LOCALE_RUSSIAN_ID = 2;
    public final static Integer ORDER_STATUS_IN_PROCESSING = 1;
    public final static Long DEFAULT_COVER_ID = 7L;
    public final static Integer GENRE_ALL_ID = 0;
    public final static Integer EMPTY_REQUEST_LENGTH = 0;


    public Integer getGenreIDAll() {
        return GENRE_ALL_ID;
    }

    public Integer getRoleUserID() {
        return ROLE_USER_ID;
    }

    public Integer getRoleAdminID() {
        return ROLE_ADMIN_ID;
    }

    public Integer getAccessStatusActiveID() {
        return ACCESS_STATUS_ACTIVE_ID;
    }

    public Integer getAccessStatusDeletedID() {
        return ACCESS_STATUS_DELETED_ID;
    }

    public Integer getLocaleEnglishID() {
        return LOCALE_ENGLISH_ID;
    }

    public Integer getLocaleRussianID() {
        return LOCALE_RUSSIAN_ID;
    }

    public final static String ERROR_SERVICE = "/error";
    public final static String REGISTER_SERVICE = "/register";
    public final static String SIGN_IN_SERVICE = "/signIn";
    public final static String SIGN_OUT_SERVICE = "/signOut";
    public final static String SEARCH_SERVICE = "/Search";
    public final static String CHANGE_LOCALE_SERVICE = "/changeLocale";
    public final static String ADD_VOLUME_SERVICE = "/AddVolume";
    public final static String ADD_MANGA_SERVICE = "/AddManga";
    public final static String ADD_NEW_AUTHOR_SERVICES = "/AddNewAuthor";
    public final static String ADD_TO_CART_SERVICE = "/AddToCart";
    public final static String ADD_NEW_PUBLISHER_SERVICE = "/AddNewPublisher";
    public final static String DISPLAY_ALL_USERS_SERVICE = "/DisplayAllUsers";
    public final static String DISPLAY_ALL_VOLUMES_SERVICE = "/DisplayAllVolumes";
    public final static String DISPLAY_ALL_ORDERS_SERVICES = "/DisplayAllOrders";
    public final static String DISPLAY_ALL_AUTHORS_SERVICES = "/DisplayAllAuthors";
    public final static String DISPLAY_ACTIVE_VOLUMES_SERVICE = "/DisplayActiveVolumes";
    public final static String DISPLAY_MY_ORDERS_SERVICES = "/DisplayMyOrders";
    public final static String DISPLAY_CART_SERVICE = "/DisplayCart";
    public final static String DISPLAY_ALL_PUBLISHERS_SERVICES = "/DisplayAllPublishers";
    public final static String DELETE_CART_ITEM_SERVICE = "/DeleteCartItem";
    public final static String DELETE_AUTHOR_FROM_MANGA_SERVICES = "/DeleteAuthorFromManga";
    public final static String PREPARE_EDIT_VOLUME_PAGE_SERVICE = "/PrepareEditVolumePage";
    public final static String PREPARE_ADD_VOLUME_PAGE_SERVICE = "/PrepareAddVolumePage";
    public final static String PREPARE_EDIT_MANGA_PAGE_SERVICE = "/PrepareEditMangaPage";
    public final static String PREPARE_ADD_MANGA_PAGE_SERVICE = "/PrepareAddMangaPage";
    public final static String PREPARE_MANGAS_PAGE_SERVICE = "/PrepareMangasPage";
    public final static String EDIT_VOLUME_SERVICE = "/EditVolume";
    public final static String EDIT_ORDER_STATUS_SERVICE = "/EditOrderStatus";
    public final static String EDIT_USER_ACCESS_SERVICE = "/EditUserAccess";
    public final static String EDIT_MANGA_SERVICE = "/EditManga";
    public final static String EDIT_AUTHOR_SERVICE = "/EditAuthor";
    public final static String EDIT_PUBLISHER_SERVICE = "/EditPublisher";
    public final static String EDIT_PROFILE_SERVICE = "/EditProfile";
    public final static String SET_QUANTITY_SERVICE = "/SetQuantity";
    public final static String CHECK_OUT_ALL_SERVICE = "/CheckOutAll";
    public final static String CHECK_OUT_SELECTED_SERVICE = "/CheckOutSelected";
    public final static String CONFIRM_ORDER_SERVICE = "/ConfirmOrder";
    public final static String FILTER_MANGA_SERVICE = "/FilterManga";
    public final static String ADD_AUTHOR_TO_MANGA_SERVICE = "/AddAuthorToManga";
    public final static String ADD_GENRE_TO_MANGA_SERVICE = "/AddGenreToManga";
    public final static String DELETE_GENRE_FROM_MANGA_SERVICE = "/DeleteGenreFromManga";

    public final static String REGISTER_JSP = "register.jsp";
    public final static String ERROR_JSP = "error.jsp";
    public final static String SIGN_IN_JSP = "signIn.jsp";
    public final static String INDEX_JSP = "index.jsp";
    public final static String EDIT_USERS_JSP = "edit_users.jsp";
    public final static String MANGAS_JSP = "mangas.jsp";
    public final static String VOLUMES_JSP = "volumes.jsp";
    public final static String CART_JSP = "cart.jsp";
    public final static String CONFIRM_ORDER_JSP = "confirm_order.jsp";
    public final static String MY_ORDERS_JSP = "my_orders.jsp";
    public final static String ORDERS_JSP = "edit_orders.jsp";
    public final static String EDIT_VOLUME_JSP = "edit_volume.jsp";
    public final static String ADD_VOLUME_JSP = "add_volume.jsp";
    public final static String EDIT_MANGA_JSP = "edit_manga.jsp";
    public final static String ADD_MANGA_JSP = "add_manga.jsp";
    public final static String EDIT_AUTHORS_JSP = "edit_authors.jsp";
    public final static String EDIT_PUBLISHERS_JSP = "edit_publishers.jsp";
    public final static String EDIT_PROFILE_JSP = "edit_profile.jsp";
    public final static String LOGIN_ERROR = "loginError";
    public final static String EMAIL_ERROR = "emailError";
    public final static String EMAIL_FORMAT_ERROR = "emailFormatError";
    public final static String EMAIL_EXISTS_ERROR = "passwordFormatError";
    public final static String PASSWORD_FORMAT_ERROR = "passwordFormatError";
    public final static String NOT_OLD_PASSWORD_ERROR = "passwordFormatError";

    public final static String PASSWORD_ERROR = "passwordError";
    public final static String EMAIL_PASSWORD_ERROR = "emailPasswordError";
    public final static String PHONE_ERROR = "phoneError";
    public final static String POSTAL_CODE_ERROR = "postalCodeError";
    public final static String EMPTY_FIELD_ERROR = "emptyFieldError";
    public final static String ISBN_ERROR = "isbnError";
    public final static String FORMAT_ERROR = "formatError";
    public final static String SIZE_ERROR = "sizeError";
    public final static String COVER_ERROR = "coverError";
    public final static String NUMBER_ERROR = "numberError";
    public final static String NEGATIVE_VALUE_ERROR = "negativeValueError";
    public final static String ERROR_OCCURRED = "errorOccurred";
    public final static String ACCESS_STATUS_ERROR = "accessStatusError";
    public final static String USER_NOT_EXISTS_ERROR = "userNotExistsError";
    public final static String LANGUAGE_NOT_EXISTS_ERROR = "languageNotExistsError";
    public final static String PUBLISHER_NOT_EXISTS_ERROR = "publisherNotExistsError";
    public final static String RELEASING_STATUS_NOT_EXISTS_ERROR = "releasingStatusNotExistsError";
    public final static String HIDDEN_INPUT_ERROR = "hiddenInputError";
    public final static String NOT_UNIQUE_MANGA_AUTHOR_ERROR = "notUniqueMangaAuthorError";
    public final static String NOT_UNIQUE_AUTHOR_ERROR = "notUniqueAuthorError";
    public final static String NOT_UNIQUE_PUBLISHER_ERROR = "notUniquePublisherError";
    public final static String AUTHOR_NAME_ERROR = "authorNameError";
    public final static Integer MIN_IMAGE_SIZE = 0;
    public final static Integer MAX_IMAGE_SIZE = 10 * 1024 * 1024;
    public final static String KEY_ERROR_NAME_FORMAT = "small.error.name.format";
    public final static String KEY_ERROR_EMAIL_FORMAT = "small.error.email.format";
    public final static String KEY_ERROR_PASSWORD_FORMAT = "small.error.password.format";
    public final static String KEY_ERROR_PASSWORD_CONFIRM = "small.error.password.confirm";
    public final static String KEY_ERROR_EMAIL_EXISTS = "small.error.email.exists";
    public final static String KEY_ERROR_LOGIN_EXISTS = "small.error.login.exists";
    public final static String KEY_ERROR_SIGN_IN = "small.error.sign.in";
    public final static String KEY_ERROR_USER_NOT_EXISTS = "small.error.user.not.exists";
    public final static String KEY_ERROR_POSTAL_CODE_FORMAT = "small.error.postal.format";
    public final static String KEY_ERROR_PHONE_FORMAT = "small.error.phone.format";

    public final static String LOCALE = "locale";
    public final static String LOCALE_ID = "localeID";

    public final static String USER = "user";
    public final static String USER_ID = "userID";
    public final static String USER_EMAIL = "userEmail";
    public final static String USER_LOGIN = "userLogin";
    public final static String USER_PASSWORD = "userPassword";
    public final static String CONFIRM_PASSWORD = "confirmPassword";
    public final static String OLD_PASSWORD = "oldPassword";
    public final static String USER_ADDRESS = "userAddress";
    public final static String USER_PHONE = "userPhone";
    public final static String USER_POSTAL_CODE = "userPostalCode";
    public final static String USERS = "users";
    public final static String STATUS_ID = "statusID";
    public final static String IS_USER_BANNED = "isUserBanned";
    public final static String USER_ORDERS = "userOrders";

    public final static String MANGAS = "mangas";
    public final static String MANGA_ID = "mangaID";
    public final static String MANGA = "manga";
    public final static String DESCRIPTION = "description";
    public final static String LANGUAGE_ID = "languageID";
    public final static String PUBLISHER_NAME = "publisherName";
    public final static String RELEASING_STATUS_ID = "releasingStatusID";
    public final static String TITLE = "title";
    public final static String ISBN = "isbn";
    public final static String PAGE_COUNT = "pageCount";
    public final static String FORMAT = "format";
    public final static String SIZE = "size";
    public final static String BINDING = "binding";
    public final static String PRICE = "price";
    public final static String RELEASE_DATE = "releaseDate";
    public final static String NUMBER = "number";
    public final static String COVER = "cover";
    public final static String ACCESS_STATUS_ID = "accessStatusID";
    public final static Integer DEFAULT_VOLUME_QUANTITY = 1;
    public final static String VOLUME = "volume";
    public final static String VOLUME_ID = "volumeID";
    public final static String COVER_ID = "coverID";

    public final static String CART_ITEM_ID = "cartItemID";
    public final static String CART_TOTAL_PRICE = "cartTotalPrice";
    public final static String QUANTITY = "quantity";
    public final static String CART_ITEMS = "cartItems";

    public final static String ORDERS = "orders";
    public final static String ORDER_STATUSES = "orderStatuses";
    public final static String ORDER = "order";
    public final static String ORDER_ID = "orderID";
    public final static String ORDER_STATUS_ID = "orderStatusID";

    public final static String GENRES = "genres";
    public final static String GENRE_ID = "genreID";
    public final static String RELEASING_STATUSES = "releasingStatuses";

    public final static String AUTHORS = "authors";
    public final static String AUTHOR_ID = "authorID";
    public final static String FIRST_NAME = "firstName";
    public final static String MIDDLE_NAME = "middleName";
    public final static String LAST_NAME = "lastName";

    public final static String PUBLISHERS = "publishers";
    public final static String PUBLISHER_ID = "publisherID";

    public final static String CONTENT_TYPE_PNG = "image/png";
    public final static String CONTENT_TYPE_JPG = "image/jpg";
    public final static String CONTENT_TYPE_JPEG = "image/jpeg";
    public final static String REFERER = "referer";
}
