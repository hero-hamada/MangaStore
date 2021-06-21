package com.epam.MangaStore.util;

import javax.servlet.http.Part;
import java.util.Base64;

import static com.epam.MangaStore.constants.Constants.*;

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

    public static boolean isImageFormatValid(Part part) {
        if (part.getName().equalsIgnoreCase(COVER)) {
            return part.getSize() > MIN_IMAGE_SIZE && part.getSize() < MAX_IMAGE_SIZE && (
                    part.getContentType().equalsIgnoreCase(CONTENT_TYPE_PNG) ||
                            part.getContentType().equalsIgnoreCase(CONTENT_TYPE_JPG) ||
                            part.getContentType().equalsIgnoreCase(CONTENT_TYPE_JPEG));
        }
        return false;
    }

}
