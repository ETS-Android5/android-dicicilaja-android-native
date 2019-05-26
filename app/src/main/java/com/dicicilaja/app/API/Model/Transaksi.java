package com.dicicilaja.app.API.Model;

public class Transaksi {
    private String tgl;
    private int id;
    private int poin;
    private String merk;
    private String status;

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Transaksi(String tanggal, int id, int poin, String merk, String status) {
        this.tgl = tgl;
        this.id = id;
        this.poin = poin;
        this.merk = merk;
        this.status = status;
    }
}
