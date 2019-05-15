package com.dicicilaja.app.API.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/15/2018.
 */

public class DetailPengajuanStatus {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DetailPengajuanStatusResponse> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailPengajuanStatusResponse> getData() {
        return data;
    }

    public void setData(List<DetailPengajuanStatusResponse> data) {
        this.data = data;
    }
}
