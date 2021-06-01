package com.epam.MangaStore.entity;

import java.sql.Date;
import java.util.Objects;

public abstract class Book {

    public Long id;
    public String title;
    public Date releaseDate;
    public Long coverID;
    public String cover;
    public Boolean isActive;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setCoverID(Long coverID) {
        this.coverID = coverID;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getCover() {
        return cover;
    }

    public Long getCoverID() {
        return coverID;
    }

    public Boolean isActive() {
        return isActive;
    }

}