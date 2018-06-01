package com.dicicilaja.dicicilaja.API.Item.DetailRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 1/30/2018.
 */

public class SurveyChecklist {
    @SerializedName("ktp_suami")
    @Expose
    private Integer ktpSuami;
    @SerializedName("ktp_penjamin")
    @Expose
    private Integer ktpPenjamin;
    @SerializedName("surat_cerai")
    @Expose
    private Integer suratCerai;
    @SerializedName("surat_kematian")
    @Expose
    private Integer suratKematian;
    @SerializedName("surat_domisili")
    @Expose
    private Integer suratDomisili;
    @SerializedName("kartu_keluarga")
    @Expose
    private Integer kartuKeluarga;
    @SerializedName("bukti_kepemilikan_rumah")
    @Expose
    private Integer buktiKepemilikanRumah;
    @SerializedName("bukti_penghasilan")
    @Expose
    private Integer buktiPenghasilan;
    @SerializedName("no_rangka")
    @Expose
    private Integer noRangka;
    @SerializedName("stnk")
    @Expose
    private Integer stnk;
    @SerializedName("bpkb")
    @Expose
    private Integer bpkb;
    @SerializedName("reschedule_date")
    @Expose
    private Object rescheduleDate;

    public Integer getKtpSuami() {
        return ktpSuami;
    }

    public void setKtpSuami(Integer ktpSuami) {
        this.ktpSuami = ktpSuami;
    }

    public Integer getKtpPenjamin() {
        return ktpPenjamin;
    }

    public void setKtpPenjamin(Integer ktpPenjamin) {
        this.ktpPenjamin = ktpPenjamin;
    }

    public Integer getSuratCerai() {
        return suratCerai;
    }

    public void setSuratCerai(Integer suratCerai) {
        this.suratCerai = suratCerai;
    }

    public Integer getSuratKematian() {
        return suratKematian;
    }

    public void setSuratKematian(Integer suratKematian) {
        this.suratKematian = suratKematian;
    }

    public Integer getSuratDomisili() {
        return suratDomisili;
    }

    public void setSuratDomisili(Integer suratDomisili) {
        this.suratDomisili = suratDomisili;
    }

    public Integer getKartuKeluarga() {
        return kartuKeluarga;
    }

    public void setKartuKeluarga(Integer kartuKeluarga) {
        this.kartuKeluarga = kartuKeluarga;
    }

    public Integer getBuktiKepemilikanRumah() {
        return buktiKepemilikanRumah;
    }

    public void setBuktiKepemilikanRumah(Integer buktiKepemilikanRumah) {
        this.buktiKepemilikanRumah = buktiKepemilikanRumah;
    }

    public Integer getBuktiPenghasilan() {
        return buktiPenghasilan;
    }

    public void setBuktiPenghasilan(Integer buktiPenghasilan) {
        this.buktiPenghasilan = buktiPenghasilan;
    }

    public Integer getNoRangka() {
        return noRangka;
    }

    public void setNoRangka(Integer noRangka) {
        this.noRangka = noRangka;
    }

    public Integer getStnk() {
        return stnk;
    }

    public void setStnk(Integer stnk) {
        this.stnk = stnk;
    }

    public Integer getBpkb() {
        return bpkb;
    }

    public void setBpkb(Integer bpkb) {
        this.bpkb = bpkb;
    }

    public Object getRescheduleDate() {
        return rescheduleDate;
    }

    public void setRescheduleDate(Object rescheduleDate) {
        this.rescheduleDate = rescheduleDate;
    }
}
