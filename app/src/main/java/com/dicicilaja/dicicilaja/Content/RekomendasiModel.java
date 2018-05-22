package com.dicicilaja.dicicilaja.Content;

/**
 * Created by fawazrifqi on 27/02/18.
 */

public class RekomendasiModel {
    private String title, id, mitra, harga, tenor, gambar;

    public RekomendasiModel(String title, String id, String mitra, String harga, String tenor, String gambar) {
        this.title = title;
        this.id = id;
        this.mitra = mitra;
        this.harga = harga;
        this.tenor = tenor;
        this.gambar = gambar;
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
}
