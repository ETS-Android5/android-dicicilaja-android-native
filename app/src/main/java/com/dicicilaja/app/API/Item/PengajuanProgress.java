package com.dicicilaja.app.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 1/25/2018.
 */

public class PengajuanProgress {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tracking_id")
    @Expose
    private String trackingId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("colleteral")
    @Expose
    private String colleteral;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("program")
    @Expose
    private String program;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStyle() { return style; }

    public void setStyle(String style) { this.style = style; }

    public String getColleteral() {
        return colleteral;
    }

    public void setColleteral(String colleteral) {
        this.colleteral = colleteral;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
