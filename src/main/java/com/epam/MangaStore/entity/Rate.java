package com.epam.MangaStore.entity;

import java.util.Objects;

public class Rate {
    private Long id;
    private Long bookID;
    private Long userID;
    private Integer rate;

    public Long getId() {
        return id;
    }

    public Long getBookID() {
        return bookID;
    }

    public Long getUserID() {
        return userID;
    }

    public Integer getRate() {
        return rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate1 = (Rate) o;
        return Objects.equals(id, rate1.id) && Objects.equals(bookID, rate1.bookID) && Objects.equals(userID, rate1.userID) && Objects.equals(rate, rate1.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookID, userID, rate);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", bookID=" + bookID +
                ", userID=" + userID +
                ", rate=" + rate +
                '}';
    }
}
