package com.dicicilaja.app.API.Model.Promo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 05/04/18.
 */

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("merchant")
    @Expose
    private Merchant merchant;
    @SerializedName("program")
    @Expose
    private Program program;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("excerpt")
    @Expose
    private String excerpt;
    @SerializedName("desc")
    @Expose
    private Object desc;
    @SerializedName("views")
    @Expose
    private Integer views;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
