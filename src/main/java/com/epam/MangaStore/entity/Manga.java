package com.epam.MangaStore.entity;

import java.util.List;
import java.util.Objects;

public class Manga extends Book {

    private String description;
    private Integer languageID;
    private Long publisherID;
    private Publisher publisher;
    private Integer releaseStatusID;
    private ReleasingStatus releaseStatus;
    private List<Volume> volumes;
    private List<Author> authors;
    private List<Genre> genres;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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

    public void setReleaseStatusID(Integer releaseStatusID) {
        this.releaseStatusID = releaseStatusID;
    }

    public void setReleaseStatus(ReleasingStatus releaseStatus) {
        this.releaseStatus = releaseStatus;
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

    public List<Volume> getVolumes() {
        return volumes;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Integer getReleaseStatusID() {
        return releaseStatusID;
    }

    public ReleasingStatus getReleaseStatus() {
        return releaseStatus;
    }

    public Long getPublisherID() {
        return publisherID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manga manga = (Manga) o;
        return description.equals(manga.description) && languageID.equals(manga.languageID) && publisherID.equals(manga.publisherID) && publisher.equals(manga.publisher) && releaseStatusID.equals(manga.releaseStatusID) && releaseStatus.equals(manga.releaseStatus) && authors.equals(manga.authors) && genres.equals(manga.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, languageID, publisherID, publisher, releaseStatusID, releaseStatus, authors, genres);
    }

    @Override
    public String toString() {
        return super.toString() + "Manga{" +
                "description=" + description +
                ", languageID=" + languageID +
                ", publisherID=" + publisherID +
                ", publisher=" + publisher +
                ", releasingStatusID=" + releaseStatusID +
                ", releasingStatus=" + releaseStatus +
                ", volumes=" + volumes +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
