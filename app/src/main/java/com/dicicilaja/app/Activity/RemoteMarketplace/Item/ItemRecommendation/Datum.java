package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemRecommendation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public class Datum {
    @Expose
    private Integer id;
    @SerializedName("title_program")
    @Expose
    private String titleProgram;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("jenis_program")
    @Expose
    private String jenisProgram;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("partner")
    @Expose
    private String partner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleProgram() {
        return titleProgram;
    }

    public void setTitleProgram(String titleProgram) {
        this.titleProgram = titleProgram;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getJenisProgram() {
        return jenisProgram;
    }

    public void setJenisProgram(String jenisProgram) {
        this.jenisProgram = jenisProgram;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
}
