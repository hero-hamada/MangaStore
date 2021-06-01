package com.epam.MangaStore.entity;

import java.util.List;
import java.util.Objects;

public class Author {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<Manga> mangas;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMangas(List<Manga> mangas) {
        this.mangas = mangas;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id) && firstName.equals(author.firstName) && middleName.equals(author.middleName) && lastName.equals(author.lastName) && mangas.equals(author.mangas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, mangas);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mangas=" + mangas +
                '}';
    }
}
