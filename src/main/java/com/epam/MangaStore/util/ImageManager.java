package com.epam.MangaStore.util;

import java.sql.Blob;
import java.util.Base64;

public class ImageManager {

    private ImageManager(){
        throw new UnsupportedOperationException();
    }

    public static String encodeByteToString(byte[] byteCover) {

        String encodedCover = "";

        if (byteCover != null) {
            encodedCover = Base64.getEncoder().encodeToString(byteCover);
        }
        return encodedCover;
    }

}
