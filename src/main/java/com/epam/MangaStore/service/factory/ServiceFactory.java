package com.epam.MangaStore.service.factory;

import com.epam.MangaStore.service.*;
import com.epam.MangaStore.service.DisplayAllUsersService;

import java.util.HashMap;
import java.util.Map;

import static com.epam.MangaStore.constants.Constants.*;

public class ServiceFactory {

    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    static {
        SERVICE_MAP.put(ERROR_SERVICE, new ErrorService());
        SERVICE_MAP.put(REGISTER_SERVICE, new RegisterService());
        SERVICE_MAP.put(SIGN_IN_SERVICE, new SignInService());
        SERVICE_MAP.put(SIGN_OUT_SERVICE, new SignOutService());
        SERVICE_MAP.put(CHANGE_LOCALE_SERVICE, new ChangeLocaleService());
        SERVICE_MAP.put(DISPLAY_ALL_USERS_SERVICE, new DisplayAllUsersService());
        SERVICE_MAP.put(EDIT_USER_ACCESS_SERVICE, new EditUserAccessService());
        SERVICE_MAP.put(DISPLAY_ALL_MANGAS_SERVICE, new DisplayAllMangasService());
        SERVICE_MAP.put(DISPLAY_ALL_VOLUMES_SERVICE, new DisplayAllVolumesService());
        SERVICE_MAP.put(ADD_TO_CART_SERVICE, new AddToCartService());
        SERVICE_MAP.put(DISPLAY_CART_SERVICE, new DisplayCartService());
        SERVICE_MAP.put(SET_QUANTITY_SERVICE, new SetQuantityService());
        SERVICE_MAP.put(DELETE_CART_ITEM_SERVICE, new DeleteCartItemService());
        SERVICE_MAP.put(CHECK_OUT_BY_CART_SERVICE, new CheckOutByCartService());
        SERVICE_MAP.put(CONFIRM_ORDER_SERVICE, new ConfirmOrderService());
        SERVICE_MAP.put(DISPLAY_MY_ORDERS_SERVICES, new DisplayMyOrdersService());
        SERVICE_MAP.put(DISPLAY_ALL_ORDERS_SERVICES, new DisplayAllOrdersService());
        SERVICE_MAP.put(EDIT_ORDER_STATUS_SERVICE, new EditOrderStatusService());
        SERVICE_MAP.put(FILTER_MANGA_SERVICE, new FilterMangaService());
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
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
}
