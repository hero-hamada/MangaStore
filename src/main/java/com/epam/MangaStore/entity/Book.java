package com.epam.MangaStore.entity;

import java.sql.Date;
import java.util.Objects;

public abstract class Book {

    private Long id;
    private String title;
    private Date releaseDate;
    private Long coverID;
    private String cover;
    private Integer accessStatusID;

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

    public void setAccessStatusID(Integer accessStatusID) {
        this.accessStatusID = accessStatusID;
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

    public Integer getAccessStatusID() {
        return accessStatusID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id.equals(book.id) && title.equals(book.title) && releaseDate.equals(book.releaseDate) && coverID.equals(book.coverID) && cover.equals(book.cover) && accessStatusID.equals(book.accessStatusID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, coverID, cover, accessStatusID);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", coverID=" + coverID +
                ", cover='" + cover + '\'' +
                ", accessStatusID=" + accessStatusID +
                '}';
    }
}