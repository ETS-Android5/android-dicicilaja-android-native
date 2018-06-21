package com.dicicilaja.app.Content;

/**
 * Created by fawazrifqi on 27/02/18.
 */

public class PartnerModel {
    private String id, gambar;

    public PartnerModel(String id,String gambar) {
        this.id = id;
        this.gambar = gambar;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
