package com.grupp5.accountmanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserM {

    @Id
    @GeneratedValue
    private long Id;
    
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="USER_EMAIL")
    private String userEmail;
    @Column(name="USER_IMAGE_URL")
    private String userImageUrl;
    @Column(name="PASSWORD")
    private String hashedPassword;

    public UserM() {
    }

    public UserM(String hashedPassword, String userName, String userEmail, String userImageUrl) {
        this.hashedPassword = hashedPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userImageUrl = userImageUrl;
    }

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
