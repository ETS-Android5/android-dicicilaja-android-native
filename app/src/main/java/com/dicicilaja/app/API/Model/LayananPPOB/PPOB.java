package com.dicicilaja.app.API.Model.LayananPPOB;

public class PPOB {
    private String name;
    private int id;
    private int thumbnail;

    public PPOB() {

    }

    public PPOB(String name, int id, int thumbnail) {
        this.name = name;
        this.id = id;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
