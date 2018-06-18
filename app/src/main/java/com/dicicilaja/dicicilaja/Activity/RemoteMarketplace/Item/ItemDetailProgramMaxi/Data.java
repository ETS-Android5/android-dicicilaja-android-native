package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public class Data {
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
    @SerializedName("tenor")
    @Expose
    private List<Tenor> tenor = null;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("related")
    @Expose
    private List<Related> related = null;

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

    public List<Tenor> getTenor() {
        return tenor;
    }

    public void setTenor(List<Tenor> tenor) {
        this.tenor = tenor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Related> getRelated() {
        return related;
    }

    public void setRelated(List<Related> related) {
        this.related = related;
    }
}
