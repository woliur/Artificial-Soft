package com.woliur.artificialsoftwoliur.model;

public class ProfileModel {
    private String id;
    private String user;
    private String name;
    private String who;
    private String image;

    public ProfileModel(String id, String user, String name, String who, String image) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.who = who;
        this.image = image;
    }

    public ProfileModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
