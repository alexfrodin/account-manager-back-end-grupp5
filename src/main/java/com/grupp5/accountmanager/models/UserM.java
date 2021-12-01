package com.grupp5.accountmanager.models;

import javax.persistence.*;

@Entity
public class UserM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    
    @Column(name= "user_name")
    private String userName;
    @Column(name= "user_email")
    private String userEmail;
    @Column(name="user_image_url")
    private String userImageUrl;
    @Column(name= "hashed_password")
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
