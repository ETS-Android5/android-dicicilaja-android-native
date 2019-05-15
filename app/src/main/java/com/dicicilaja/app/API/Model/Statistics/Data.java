package com.dicicilaja.app.API.Model.Statistics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 03/05/18.
 */

public class Data {
    @SerializedName("pengajuan_masuk")
    @Expose
    private Integer pengajuanMasuk;
    @SerializedName("pengajuan_diproses")
    @Expose
    private Integer pengajuanDiproses;
    @SerializedName("terkirim")
    @Expose
    private Integer terkirim;
    @SerializedName("verifikasi")
    @Expose
    private Integer verifikasi;
    @SerializedName("proses")
    @Expose
    private Integer proses;
    @SerializedName("pending")
    @Expose
    private Integer pending;
    @SerializedName("survey")
    @Expose
    private Integer survey;
    @SerializedName("analisa_kredit")
    @Expose
    private Integer analisaKredit;
    @SerializedName("ditolak")
    @Expose
    private Integer ditolak;
    @SerializedName("pencairan")
    @Expose
    private Integer pencairan;

    public Integer getPengajuanMasuk() {
        return pengajuanMasuk;
    }

    public void setPengajuanMasuk(Integer pengajuanMasuk) {
        this.pengajuanMasuk = pengajuanMasuk;
    }

    public Integer getPengajuanDiproses() {
        return pengajuanDiproses;
    }

    public void setPengajuanDiproses(Integer pengajuanDiproses) {
        this.pengajuanDiproses = pengajuanDiproses;
    }

    public Integer getTerkirim() {
        return terkirim;
    }

    public void setTerkirim(Integer terkirim) {
        this.terkirim = terkirim;
    }

    public Integer getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(Integer verifikasi) {
        this.verifikasi = verifikasi;
    }

    public Integer getProses() {
        return proses;
    }

    public void setProses(Integer proses) {
        this.proses = proses;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getSurvey() {
        return survey;
    }

    public void setSurvey(Integer survey) {
        this.survey = survey;
    }

    public Integer getAnalisaKredit() {
        return analisaKredit;
    }

    public void setAnalisaKredit(Integer analisaKredit) {
        this.analisaKredit = analisaKredit;
    }

    public Integer getDitolak() {
        return ditolak;
    }

    public void setDitolak(Integer ditolak) {
        this.ditolak = ditolak;
    }

    public Integer getPencairan() {
        return pencairan;
    }

    public void setPencairan(Integer pencairan) {
        this.pencairan = pencairan;
    }
}
