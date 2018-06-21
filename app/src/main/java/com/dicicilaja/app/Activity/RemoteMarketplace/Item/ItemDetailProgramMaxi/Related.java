package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Related {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title_program")
    @Expose
    private String titleProgram;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_without_rp")
    @Expose
    private String priceWithoutRp;
    @SerializedName("jenis_program")
    @Expose
    private String jenisProgram;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("partner")
    @Expose
    private String partner;
    @SerializedName("id_partner")
    @Expose
    private Integer idPartner;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("link")
    @Expose
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPriceWithoutRp() {
        return priceWithoutRp;
    }

    public void setPriceWithoutRp(String priceWithoutRp) {
        this.priceWithoutRp = priceWithoutRp;
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

    public Integer getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(Integer idPartner) {
        this.idPartner = idPartner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
