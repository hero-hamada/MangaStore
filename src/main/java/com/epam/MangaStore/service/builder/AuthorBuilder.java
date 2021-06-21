package com.epam.MangaStore.service.builder;

import com.epam.MangaStore.entity.Author;

import javax.servlet.http.HttpServletRequest;

import static com.epam.MangaStore.constants.Constants.*;

public class AuthorBuilder {

    private static AuthorBuilder instance = new AuthorBuilder();

    public Author fillNew(HttpServletRequest request) {
        Author author = new Author();
        author.setFirstName(request.getParameter(FIRST_NAME).trim());
        author.setMiddleName(request.getParameter(MIDDLE_NAME).trim());
        author.setLastName(request.getParameter(LAST_NAME).trim());
        return author;
    }

    public Author fillToUpdate(HttpServletRequest request) {
        Author author = fillNew(request);
        author.setId(Long.valueOf(request.getParameter(AUTHOR_ID).trim()));
        return author;
    }

    public static AuthorBuilder getInstance() {
        if (instance == null) {
            instance = new AuthorBuilder();
        }
        return instance;
    }
}
