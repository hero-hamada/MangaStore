package com.epam.MangaStore.entity;

import java.util.Objects;


public class User {

    private Long id;
    private String email;
    private String login;
    private String password;
    private String confirmPassword;
    private String oldPassword;
    private String postalCode;
    private String address;
    private String phone;
    private Integer roleID;
    private boolean isBanned;
    private Integer statusID;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isBanned == user.isBanned && id.equals(user.id) && email.equals(user.email) && login.equals(user.login) && password.equals(user.password) && postalCode.equals(user.postalCode) && address.equals(user.address) && phone.equals(user.phone) && roleID.equals(user.roleID) && statusID.equals(user.statusID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, password, postalCode, address, phone, roleID, isBanned, statusID);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", roleID=" + roleID +
                ", isBanned=" + isBanned +
                ", statusID=" + statusID +
                '}';
    }
}
