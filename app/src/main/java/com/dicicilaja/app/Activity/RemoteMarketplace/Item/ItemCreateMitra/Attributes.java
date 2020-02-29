
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateMitra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("nama_perusahaan")
    @Expose
    private String namaPerusahaan;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("alamat_jalan_pribadi")
    @Expose
    private String alamatJalanPribadi;
    @SerializedName("desa_id")
    @Expose
    private String desaId;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("no_ktp")
    @Expose
    private String noKtp;
    @SerializedName("no_npwp_perusahaan")
    @Expose
    private String noNpwpPerusahaan;
    @SerializedName("no_npwp_pribadi")
    @Expose
    private String noNpwpPribadi;
    @SerializedName("desa_perusahaan_id")
    @Expose
    private String desaPerusahaanId;
    @SerializedName("alamat_jalan_perusahaan")
    @Expose
    private String alamatJalanPerusahaan;
    @SerializedName("foto_npwp")
    @Expose
    private Object fotoNpwp;
    @SerializedName("foto_ktp")
    @Expose
    private Object fotoKtp;

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamatJalanPribadi() {
        return alamatJalanPribadi;
    }

    public void setAlamatJalanPribadi(String alamatJalanPribadi) {
        this.alamatJalanPribadi = alamatJalanPribadi;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoNpwpPerusahaan() {
        return noNpwpPerusahaan;
    }

    public void setNoNpwpPerusahaan(String noNpwpPerusahaan) {
        this.noNpwpPerusahaan = noNpwpPerusahaan;
    }

    public String getNoNpwpPribadi() {
        return noNpwpPribadi;
    }

    public void setNoNpwpPribadi(String noNpwpPribadi) {
        this.noNpwpPribadi = noNpwpPribadi;
    }

    public String getDesaPerusahaanId() {
        return desaPerusahaanId;
    }

    public void setDesaPerusahaanId(String desaPerusahaanId) {
        this.desaPerusahaanId = desaPerusahaanId;
    }

    public String getAlamatJalanPerusahaan() {
        return alamatJalanPerusahaan;
    }

    public void setAlamatJalanPerusahaan(String alamatJalanPerusahaan) {
        this.alamatJalanPerusahaan = alamatJalanPerusahaan;
    }

    public Object getFotoNpwp() {
        return fotoNpwp;
    }

    public void setFotoNpwp(Object fotoNpwp) {
        this.fotoNpwp = fotoNpwp;
    }

    public Object getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(Object fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

}
