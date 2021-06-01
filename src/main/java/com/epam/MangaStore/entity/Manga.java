package com.epam.MangaStore.entity;

import java.util.List;
import java.util.Objects;

public class Manga extends Book {

    private String description;
    private Integer languageID;
    private Long publisherID;
    private Publisher publisher;
    private Integer statusID;
    private MangaStatus status;
    private List<Volume> volumes;
    private List<Author> authors;
    private List<Genre> genres;
    private Double rating;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setStatus(MangaStatus status) {
        this.status = status;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public void setPublisherID(Long publisherID) {
        this.publisherID = publisherID;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLanguageID() {
        return languageID;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public MangaStatus getStatus() {
        return status;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public Long getPublisherID() {
        return publisherID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return description.equals(manga.description) && languageID.equals(manga.languageID) && publisherID.equals(manga.publisherID) && publisher.equals(manga.publisher) && statusID.equals(manga.statusID) && status.equals(manga.status) && volumes.equals(manga.volumes) && authors.equals(manga.authors) && genres.equals(manga.genres) && rating.equals(manga.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, languageID, publisherID, publisher, statusID, status, volumes, authors, genres, rating);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "description='" + description + '\'' +
                ", languageID=" + languageID +
                ", publisherID=" + publisherID +
                ", publisher=" + publisher +
                ", statusID=" + statusID +
                ", status=" + status +
                ", volumes=" + volumes +
                ", authors=" + authors +
                ", genres=" + genres +
                ", rating=" + rating +
                '}';
    }
}
