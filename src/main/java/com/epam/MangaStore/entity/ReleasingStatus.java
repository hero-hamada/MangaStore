package com.epam.MangaStore.entity;

import java.util.Objects;

public class ReleasingStatus {

    private Long id;
    private Integer languageID;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLanguageID(Integer languageID) {
        this.languageID = languageID;
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

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReleasingStatus that = (ReleasingStatus) o;
        return id.equals(that.id) && languageID.equals(that.languageID) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, languageID, name);
    }
}
