package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.service.*;

import java.util.HashMap;
import java.util.Map;

import static com.epam.MangaStore.constants.Constants.*;

public class ServiceFactory {

    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private ServiceFactory() {
    }

    static {
        SERVICE_MAP.put(ERROR_SERVICE, new ErrorService());
        SERVICE_MAP.put(REGISTER_SERVICE, new RegisterService());
        SERVICE_MAP.put(SIGN_IN_SERVICE, new SignInService());
        SERVICE_MAP.put(SIGN_OUT_SERVICE, new SignOutService());
        SERVICE_MAP.put(SEARCH_SERVICE, new SearchService());
        SERVICE_MAP.put(SORT_VOLUMES_SERVICE, new SortVolumeService());
        SERVICE_MAP.put(SET_QUANTITY_SERVICE, new SetQuantityService());
        SERVICE_MAP.put(EDIT_USER_ACCESS_SERVICE, new EditUserAccessService());
        SERVICE_MAP.put(EDIT_VOLUME_SERVICE, new EditVolumeService());
        SERVICE_MAP.put(EDIT_MANGA_SERVICE, new EditMangaService());
        SERVICE_MAP.put(EDIT_AUTHOR_SERVICE, new EditAuthorService());
        SERVICE_MAP.put(EDIT_ORDER_STATUS_SERVICE, new EditOrderStatusService());
        SERVICE_MAP.put(EDIT_PUBLISHER_SERVICE, new EditPublisherService());
        SERVICE_MAP.put(EDIT_PROFILE_SERVICE, new EditProfileService());
        SERVICE_MAP.put(DISPLAY_ALL_USERS_SERVICE, new DisplayAllUsersService());
        SERVICE_MAP.put(DISPLAY_ALL_AUTHORS_SERVICES, new DisplayAllAuthorsService());
        SERVICE_MAP.put(DISPLAY_ALL_PUBLISHERS_SERVICES, new DisplayAllPublishersService());
        SERVICE_MAP.put(DISPLAY_ALL_ORDERS_SERVICES, new DisplayAllOrdersService());
        SERVICE_MAP.put(DISPLAY_CART_SERVICE, new DisplayCartService());
        SERVICE_MAP.put(DISPLAY_MY_ORDERS_SERVICES, new DisplayMyOrdersService());
        SERVICE_MAP.put(DELETE_CART_ITEM_SERVICE, new DeleteCartItemService());
        SERVICE_MAP.put(DELETE_AUTHOR_FROM_MANGA_SERVICES, new DeleteAuthorFromMangaService());
        SERVICE_MAP.put(DELETE_GENRE_FROM_MANGA_SERVICE, new DeleteGenreFromMangaService());
        SERVICE_MAP.put(ADD_TO_CART_SERVICE, new AddToCartService());
        SERVICE_MAP.put(ADD_AUTHOR_TO_MANGA_SERVICE, new AddAuthorToMangaService());
        SERVICE_MAP.put(ADD_GENRE_TO_MANGA_SERVICE, new AddGenreToMangaService());
        SERVICE_MAP.put(ADD_VOLUME_SERVICE, new AddVolumeService());
        SERVICE_MAP.put(ADD_MANGA_SERVICE, new AddMangaService());
        SERVICE_MAP.put(ADD_NEW_AUTHOR_SERVICES, new AddNewAuthorService());
        SERVICE_MAP.put(ADD_NEW_PUBLISHER_SERVICE, new AddNewPublisherService());
        SERVICE_MAP.put(PREPARE_EDIT_VOLUME_PAGE_SERVICE, new PrepareEditVolumePageService());
        SERVICE_MAP.put(PREPARE_ADD_VOLUME_PAGE_SERVICE, new PrepareAddVolumePageService());
        SERVICE_MAP.put(PREPARE_EDIT_MANGA_PAGE_SERVICE, new PrepareEditMangaPageService());
        SERVICE_MAP.put(PREPARE_ADD_MANGA_PAGE_SERVICE, new PrepareAddMangaPageService());
        SERVICE_MAP.put(PREPARE_MANGAS_PAGE_SERVICE, new PrepareMangasPageService());
        SERVICE_MAP.put(PREPARE_VOLUMES_PAGE_SERVICE, new PrepareVolumesPageService());
        SERVICE_MAP.put(CHECK_OUT_ALL_SERVICE, new CheckOutAllService());
        SERVICE_MAP.put(CHECK_OUT_SELECTED_SERVICE, new CheckOutSelectedService());
        SERVICE_MAP.put(CONFIRM_ORDER_SERVICE, new ConfirmOrderService());
        SERVICE_MAP.put(CHANGE_LOCALE_SERVICE, new ChangeLocaleService());
        SERVICE_MAP.put(FILTER_MANGA_SERVICE, new FilterMangaService());
    }

    public Service getService(String request) {

        Service service = SERVICE_MAP.get(ERROR_SERVICE);

        for (Map.Entry<String, Service> pair : SERVICE_MAP.entrySet()) {
            if (request.equalsIgnoreCase(pair.getKey())) {
                service = SERVICE_MAP.get(pair.getKey());
            }
        }
        return service;
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }
}
