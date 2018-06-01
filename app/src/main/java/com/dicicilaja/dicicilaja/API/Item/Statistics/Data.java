package com.dicicilaja.dicicilaja.API.Item.Statistics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 03/05/18.
 */

public class Data {
    @SerializedName("order_in")
    @Expose
    private String orderIn;
    @SerializedName("order_process")
    @Expose
    private String orderProcess;
    @SerializedName("terkirim")
    @Expose
    private String terkirim;
    @SerializedName("verifikasi")
    @Expose
    private String verifikasi;
    @SerializedName("proses")
    @Expose
    private String proses;
    @SerializedName("survey")
    @Expose
    private String survey;
    @SerializedName("pending")
    @Expose
    private String pending;
    @SerializedName("analisa_kredit")
    @Expose
    private String analisaKredit;
    @SerializedName("ditolak")
    @Expose
    private String ditolak;
    @SerializedName("pencairan")
    @Expose
    private String pencairan;

    public String getOrderIn() {
        return orderIn;
    }

    public void setOrderIn(String orderIn) {
        this.orderIn = orderIn;
    }

    public String getOrderProcess() {
        return orderProcess;
    }

    public void setOrderProcess(String orderProcess) {
        this.orderProcess = orderProcess;
    }

    public String getTerkirim() {
        return terkirim;
    }

    public void setTerkirim(String terkirim) {
        this.terkirim = terkirim;
    }

    public String getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(String verifikasi) {
        this.verifikasi = verifikasi;
    }

    public String getProses() {
        return proses;
    }

    public void setProses(String proses) {
        this.proses = proses;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getAnalisaKredit() {
        return analisaKredit;
    }

    public void setAnalisaKredit(String analisaKredit) {
        this.analisaKredit = analisaKredit;
    }

    public String getDitolak() {
        return ditolak;
    }

    public void setDitolak(String ditolak) {
        this.ditolak = ditolak;
    }

    public String getPencairan() {
        return pencairan;
    }

    public void setPencairan(String pencairan) {
        this.pencairan = pencairan;
    }
}
