package com.dicicilaja.app.API.Model.ProductCatalog;

public class ProductCatalog {
    private String name;
    private int point;
    private int id;
    private int thumbnail;

    public ProductCatalog(){

    }

    public ProductCatalog(String name, int id, int thumbnail, int point) {
        this.name = name;
        this.id = id;
        this.thumbnail = thumbnail;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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
