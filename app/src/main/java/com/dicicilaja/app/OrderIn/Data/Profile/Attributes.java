
package com.dicicilaja.app.OrderIn.Data.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("partner_maxi_id")
    @Expose
    private Object partnerMaxiId;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("kota_lahir")
    @Expose
    private Object kotaLahir;
    @SerializedName("tanggal_lahir")
    @Expose
    private Object tanggalLahir;
    @SerializedName("no_telp")
    @Expose
    private Object noTelp;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("jenis_kelamin")
    @Expose
    private Object jenisKelamin;
    @SerializedName("avatar_url")
    @Expose
    private Object avatarUrl;
    @SerializedName("no_ktp")
    @Expose
    private String noKtp;
    @SerializedName("scan_ktp_url")
    @Expose
    private Object scanKtpUrl;
    @SerializedName("nama_pasangan")
    @Expose
    private Object namaPasangan;
    @SerializedName("no_ktp_pasangan")
    @Expose
    private Object noKtpPasangan;
    @SerializedName("status_perkawinan")
    @Expose
    private Object statusPerkawinan;
    @SerializedName("nama_ibu_kandung")
    @Expose
    private Object namaIbuKandung;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created-at")
    @Expose
    private String createdAt;
    @SerializedName("updated-at")
    @Expose
    private String updatedAt;

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getPartnerMaxiId() {
        return partnerMaxiId;
    }

    public void setPartnerMaxiId(Object partnerMaxiId) {
        this.partnerMaxiId = partnerMaxiId;
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

    public Object getKotaLahir() {
        return kotaLahir;
    }

    public void setKotaLahir(Object kotaLahir) {
        this.kotaLahir = kotaLahir;
    }

    public Object getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Object tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Object getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(Object noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public Object getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Object jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Object getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(Object avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public Object getScanKtpUrl() {
        return scanKtpUrl;
    }

    public void setScanKtpUrl(Object scanKtpUrl) {
        this.scanKtpUrl = scanKtpUrl;
    }

    public Object getNamaPasangan() {
        return namaPasangan;
    }

    public void setNamaPasangan(Object namaPasangan) {
        this.namaPasangan = namaPasangan;
    }

    public Object getNoKtpPasangan() {
        return noKtpPasangan;
    }

    public void setNoKtpPasangan(Object noKtpPasangan) {
        this.noKtpPasangan = noKtpPasangan;
    }

    public Object getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(Object statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public Object getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(Object namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
