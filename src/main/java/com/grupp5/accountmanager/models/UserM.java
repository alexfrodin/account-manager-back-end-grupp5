package com.grupp5.accountmanager.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserM {
@Id
private long Id;

private String hashedPassword;

private String userName;
private String userEmail;
private String userImageUrl;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
