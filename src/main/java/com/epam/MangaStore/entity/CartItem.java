package com.epam.MangaStore.entity;


import java.util.Objects;

public class CartItem {

    private Long id;
    private Long userID;
    private Long volumeID;
    private Volume volume;
    private Integer quantity;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setVolumeID(Long volumeID) {this.volumeID = volumeID;}

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public Volume getVolume() {
        return volume;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getVolumeID() {
        return volumeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id.equals(cartItem.id) && userID.equals(cartItem.userID) && volumeID.equals(cartItem.volumeID) && volume.equals(cartItem.volume) && quantity.equals(cartItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, volumeID, volume, quantity);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", userID=" + userID +
                ", volumeID=" + volumeID +
                ", volume=" + volume +
                ", quantity=" + quantity +
                '}';
    }
}
