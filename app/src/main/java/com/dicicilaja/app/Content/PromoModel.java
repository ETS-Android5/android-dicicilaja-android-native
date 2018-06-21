package com.dicicilaja.app.Content;

/**
 * Created by ziterz on 2/16/2018.
 */

public class PromoModel {
    private String title, id, mitra, harga, tenor, gambar, diskon;

    public PromoModel(String title, String id, String mitra, String harga, String tenor, String gambar, String diskon) {
        this.title = title;
        this.id = id;
        this.mitra = mitra;
        this.harga = harga;
        this.tenor = tenor;
        this.gambar = gambar;
        this.diskon = diskon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMitra() {
        return mitra;
    }

    public void setMitra(String mitra) {
        this.mitra = mitra;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }
}
