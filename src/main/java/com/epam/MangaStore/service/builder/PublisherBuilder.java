package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.entity.Publisher;

import javax.servlet.http.HttpServletRequest;

import static com.epam.MangaStore.constants.Constants.*;

public class PublisherBuilder {

    private static PublisherBuilder instance = new PublisherBuilder();

    private PublisherBuilder() {
    }

    public Publisher fillNew(HttpServletRequest request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getParameter(PUBLISHER_NAME).trim());
        return publisher;
    }

    public Publisher fillToUpdate(HttpServletRequest request) {
        Publisher publisher = fillNew(request);
        publisher.setId(Long.valueOf(request.getParameter(PUBLISHER_ID).trim()));
        return publisher;
    }

    public static PublisherBuilder getInstance() {
        if (instance == null) {
            instance = new PublisherBuilder();
        }
        return instance;
    }
}
