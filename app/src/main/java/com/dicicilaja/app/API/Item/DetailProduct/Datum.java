package com.dicicilaja.app.API.Item.DetailProduct;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 06/04/18.
 */

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("alt_image")
    @Expose
    private Object altImage;
    @SerializedName("brochure")
    @Expose
    private Object brochure;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("excerpt")
    @Expose
    private String excerpt;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("is_promo")
    @Expose
    private Integer isPromo;
    @SerializedName("views")
    @Expose
    private Integer views;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
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

    public Object getAltImage() {
        return altImage;
    }

    public void setAltImage(Object altImage) {
        this.altImage = altImage;
    }

    public Object getBrochure() {
        return brochure;
    }

    public void setBrochure(Object brochure) {
        this.brochure = brochure;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(Integer isPromo) {
        this.isPromo = isPromo;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

}
