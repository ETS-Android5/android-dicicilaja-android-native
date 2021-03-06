package com.dicicilaja.app.API.Model.PengajuanAxi;

import com.dicicilaja.app.Model.RequestMeta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public class PengajuanAxi {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    @SerializedName("meta")
    private RequestMeta meta;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public RequestMeta getMeta() {
        return meta;
    }

    public void setMeta(RequestMeta meta) {
        this.meta = meta;
    }
}
