package com.epam.MangaStore.entity;


import java.util.Objects;

public class OrderItem {

    private Long id;
    private Long orderID;
    private Long volumeID;
    private Volume volume;
    private Long fixedPrice;
    private Integer quantity;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public void setVolumeID(Long volumeID) {
        this.volumeID = volumeID;
    }

    public void setFixedPrice(Long fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderID() {
        return orderID;
    }

    public Long getVolumeID() {
        return volumeID;
    }

    public Long getFixedPrice() {
        return fixedPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Volume getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id) && orderID.equals(orderItem.orderID) && volumeID.equals(orderItem.volumeID) && volume.equals(orderItem.volume) && fixedPrice.equals(orderItem.fixedPrice) && quantity.equals(orderItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderID, volumeID, volume, fixedPrice, quantity);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderID=" + orderID +
                ", volumeID=" + volumeID +
                ", volume=" + volume +
                ", fixedPrice=" + fixedPrice +
                ", quantity=" + quantity +
                '}';
    }
}
