package com.epam.MangaStore.database.connection;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBResourceManager {

    private final static String DB_PROPERTIES = "db";

    private final static DBResourceManager INSTANCE = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPERTIES, Locale.ENGLISH);

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}

