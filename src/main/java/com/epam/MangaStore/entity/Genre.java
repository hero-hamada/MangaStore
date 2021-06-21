package com.epam.MangaStore.entity;


import java.util.Objects;

public class Genre {

    private Integer id;
    private Integer languageID;
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLanguageID() {
        return languageID;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id.equals(genre.id) && languageID.equals(genre.languageID) && name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, languageID, name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", languageID=" + languageID +
                ", name='" + name + '\'' +
                '}';
    }
}
