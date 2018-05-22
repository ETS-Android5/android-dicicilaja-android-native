package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemProfileMaxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class Data {
    @SerializedName("id_mitra")
    @Expose
    private String idMitra;
    @SerializedName("nama_perusahaan")
    @Expose
    private String namaPerusahaan;
    @SerializedName("alamat_perusahaan")
    @Expose
    private String alamatPerusahaan;
    @SerializedName("npwp_perusahaan")
    @Expose
    private String npwpPerusahaan;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telp")
    @Expose
    private String telp;
    @SerializedName("handphone")
    @Expose
    private String handphone;
    @SerializedName("alamat_pemilik")
    @Expose
    private String alamatPemilik;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("ktp")
    @Expose
    private String ktp;
    @SerializedName("npwp_pemilik")
    @Expose
    private String npwpPemilik;

    public String getIdMitra() {
        return idMitra;
    }

    public void setIdMitra(String idMitra) {
        this.idMitra = idMitra;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getAlamatPerusahaan() {
        return alamatPerusahaan;
    }

    public void setAlamatPerusahaan(String alamatPerusahaan) {
        this.alamatPerusahaan = alamatPerusahaan;
    }

    public String getNpwpPerusahaan() {
        return npwpPerusahaan;
    }

    public void setNpwpPerusahaan(String npwpPerusahaan) {
        this.npwpPerusahaan = npwpPerusahaan;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getAlamatPemilik() {
        return alamatPemilik;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        this.alamatPemilik = alamatPemilik;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getNpwpPemilik() {
        return npwpPemilik;
    }

    public void setNpwpPemilik(String npwpPemilik) {
        this.npwpPemilik = npwpPemilik;
    }
}
