package com.epam.MangaStore.entity;


import java.util.Objects;

public class Genre {

    private Long id;
    private Integer languageID;
    private String language;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Integer getLanguageID() {
        return languageID;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id.equals(genre.id) && languageID.equals(genre.languageID) && language.equals(genre.language) && name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, languageID, language, name);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", languageID=" + languageID +
                ", language='" + language + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
