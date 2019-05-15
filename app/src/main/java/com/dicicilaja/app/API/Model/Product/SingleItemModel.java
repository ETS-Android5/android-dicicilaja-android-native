package com.dicicilaja.app.API.Model.Product;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class SingleItemModel {
    private String tv_title, image, tv_mitra,tv_harga,tv_tenor;

    public SingleItemModel() {

    }

    public SingleItemModel(String tv_title, String image, String tv_mitra, String tv_harga, String tv_tenor) {
        this.tv_title = tv_title;
        this.image = image;
        this.tv_mitra = tv_mitra;
        this.tv_harga = tv_harga;
        this.tv_tenor = tv_tenor;
    }

    public String getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title = tv_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTv_mitra() {
        return tv_mitra;
    }

    public void setTv_mitra(String tv_mitra) {
        this.tv_mitra = tv_mitra;
    }

    public String getTv_harga() {
        return tv_harga;
    }

    public void setTv_harga(String tv_harga) {
        this.tv_harga = tv_harga;
    }

    public String getTv_tenor() {
        return tv_tenor;
    }

    public void setTv_tenor(String tv_tenor) {
        this.tv_tenor = tv_tenor;
    }
}
