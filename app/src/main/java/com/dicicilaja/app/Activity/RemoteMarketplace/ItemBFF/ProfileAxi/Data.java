
package com.dicicilaja.app.Activity.RemoteMarketplace.ItemBFF.ProfileAxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("cabang")
    @Expose
    private String cabang;
    @SerializedName("no_ktp")
    @Expose
    private String noKtp;
    @SerializedName("tempat_lahir")
    @Expose
    private String tempatLahir;
    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("alamat_ktp")
    @Expose
    private String alamatKtp;
    @SerializedName("rt_rw_ktp")
    @Expose
    private String rtRwKtp;
    @SerializedName("kelurahan_ktp")
    @Expose
    private String kelurahanKtp;
    @SerializedName("kecamatan_ktp")
    @Expose
    private String kecamatanKtp;
    @SerializedName("kodepos_ktp")
    @Expose
    private String kodeposKtp;
    @SerializedName("provinsi_ktp")
    @Expose
    private String provinsiKtp;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("npwp_no")
    @Expose
    private String npwpNo;
    @SerializedName("an_rekening")
    @Expose
    private String anRekening;
    @SerializedName("cabang_bank")
    @Expose
    private String cabangBank;
    @SerializedName("kota_bank")
    @Expose
    private String kotaBank;
    @SerializedName("point_reward")
    @Expose
    private Integer pointReward;
    @SerializedName("point_trip")
    @Expose
    private Integer pointTrip;
    @SerializedName("axi_id")
    @Expose
    private String axiId;
    @SerializedName("tanggal_daftar")
    @Expose
    private String tanggalDaftar;
    @SerializedName("axi_id_reff")
    @Expose
    private String axiIdReff;
    @SerializedName("nama_bank")
    @Expose
    private String namaBank;
    @SerializedName("id_bank")
    @Expose
    private String idBank;
    @SerializedName("no_rekening")
    @Expose
    private String noRekening;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamatKtp() {
        return alamatKtp;
    }

    public void setAlamatKtp(String alamatKtp) {
        this.alamatKtp = alamatKtp;
    }

    public String getRtRwKtp() {
        return rtRwKtp;
    }

    public void setRtRwKtp(String rtRwKtp) {
        this.rtRwKtp = rtRwKtp;
    }

    public String getKelurahanKtp() {
        return kelurahanKtp;
    }

    public void setKelurahanKtp(String kelurahanKtp) {
        this.kelurahanKtp = kelurahanKtp;
    }

    public String getKecamatanKtp() {
        return kecamatanKtp;
    }

    public void setKecamatanKtp(String kecamatanKtp) {
        this.kecamatanKtp = kecamatanKtp;
    }

    public String getKodeposKtp() {
        return kodeposKtp;
    }

    public void setKodeposKtp(String kodeposKtp) {
        this.kodeposKtp = kodeposKtp;
    }

    public String getProvinsiKtp() {
        return provinsiKtp;
    }

    public void setProvinsiKtp(String provinsiKtp) {
        this.provinsiKtp = provinsiKtp;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNpwpNo() {
        return npwpNo;
    }

    public void setNpwpNo(String npwpNo) {
        this.npwpNo = npwpNo;
    }

    public String getAnRekening() {
        return anRekening;
    }

    public void setAnRekening(String anRekening) {
        this.anRekening = anRekening;
    }

    public String getCabangBank() {
        return cabangBank;
    }

    public void setCabangBank(String cabangBank) {
        this.cabangBank = cabangBank;
    }

    public String getKotaBank() {
        return kotaBank;
    }

    public void setKotaBank(String kotaBank) {
        this.kotaBank = kotaBank;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }

    public Integer getPointTrip() {
        return pointTrip;
    }

    public void setPointTrip(Integer pointTrip) {
        this.pointTrip = pointTrip;
    }

    public String getAxiId() {
        return axiId;
    }

    public void setAxiId(String axiId) {
        this.axiId = axiId;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getAxiIdReff() {
        return axiIdReff;
    }

    public void setAxiIdReff(String axiIdReff) {
        this.axiIdReff = axiIdReff;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getIdBank() {
        return idBank;
    }

    public void setIdBank(String idBank) {
        this.idBank = idBank;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

}
