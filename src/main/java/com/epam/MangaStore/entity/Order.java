package com.epam.MangaStore.entity;

import java.sql.Date;
import java.util.List;

public class Order {
    private Long id;
    private Long userID;
    private User user;
    private Integer statusID;
    private OrderStatus status;
    private Long totalPrice;
    private Date createdDate;
    private List<OrderItem> orderItems;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setStatusID(Integer orderStatusID) {
        this.statusID = orderStatusID;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }
}
