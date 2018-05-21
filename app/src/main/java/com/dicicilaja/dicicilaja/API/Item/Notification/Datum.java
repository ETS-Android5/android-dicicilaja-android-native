package com.dicicilaja.dicicilaja.API.Item.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 2/8/2018.
 */

public class Datum {
    @SerializedName("tracking_id")
    @Expose
    private Integer trackingId;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("transaction_id")
    @Expose
    private String transaction_id;

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void getTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
