package com.epam.MangaStore.constants;

import static com.epam.MangaStore.constants.Constants.*;

public enum SortType {

    NUMBER_ASC(KEY_NUMBER_ASC),
    NUMBER_DESC(KEY_NUMBER_DESC),
    TITLE(KEY_TITLE),
    PRICE_ASC (KEY_PRICE_ASC),
    PRICE_DESC (KEY_PRICE_DESC);

    private final String key;

    SortType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
