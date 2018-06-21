package com.dicicilaja.app.Content;

/**
 * Created by fawazrifqi on 13/04/18.
 */

public class RuteModel {
    private String id, day, title, deskripsi;

    public RuteModel(String id, String day, String title, String deskripsi) {
        this.id = id;
        this.day = day;
        this.title = title;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
