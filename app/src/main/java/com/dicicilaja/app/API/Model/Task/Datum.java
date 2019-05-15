package com.dicicilaja.app.API.Model.Task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 1/25/2018.
 */

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("transaction_id")
    @Expose
    private Integer transactionId;
    @SerializedName("tracking_id")
    @Expose
    private String trackingId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("colleteral")
    @Expose
    private String colleteral;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("client_name")
    @Expose
    private String client_name;
    @SerializedName("branch")
    @Expose
    private String branch;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
