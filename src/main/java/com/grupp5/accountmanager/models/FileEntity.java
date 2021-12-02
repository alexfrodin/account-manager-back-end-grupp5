package com.grupp5.accountmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Long size;
    private String contentType;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserM uploader;

    public UserM getUploader() {
        return uploader;
    }

    public void setUploader(UserM uploader) {
        this.uploader = uploader;
    }

    @Lob
    private byte[] data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
