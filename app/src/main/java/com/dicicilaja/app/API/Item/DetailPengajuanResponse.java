package com.dicicilaja.app.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/14/2018.
 */

public class DetailPengajuanResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DetailPengajuan> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailPengajuan> getData() {
        return data;
    }

    public void setData(List<DetailPengajuan> data) {
        this.data = data;
    }
}
