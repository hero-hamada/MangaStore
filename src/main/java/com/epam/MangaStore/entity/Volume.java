package com.epam.MangaStore.entity;

import java.util.Objects;

public class Volume extends Book {

    private String isbn;
    private Integer pageCount;
    private String format;
    private String size;
    private String binding;
    private Long price;
    private Long mangaID;
    private Integer number;

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setMangaID(Long mangaID) {
        this.mangaID = mangaID;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getFormat() {
        return format;
    }

    public String getSize() {
        return size;
    }

    public String getBinding() {
        return binding;
    }

    public Long getPrice() {
        return price;
    }

    public Long getMangaID() {
        return mangaID;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Volume volume = (Volume) o;
        return isbn.equals(volume.isbn) && pageCount.equals(volume.pageCount) && format.equals(volume.format) && size.equals(volume.size) && binding.equals(volume.binding) && price.equals(volume.price) && mangaID.equals(volume.mangaID) && number.equals(volume.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn, pageCount, format, size, binding, price, mangaID, number);
    }

    @Override
    public String toString() {
        return super.toString() + "Volume{" +
                "isbn='" + isbn + '\'' +
                ", pageCount=" + pageCount +
                ", format='" + format + '\'' +
                ", size='" + size + '\'' +
                ", binding='" + binding + '\'' +
                ", price=" + price +
                ", mangaID=" + mangaID +
                ", number=" + number +
                '}';
    }
}
