package com.dicicilaja.app.API.Model.RequestProgress;

import com.dicicilaja.app.Model.RequestMeta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/25/2018.
 */

public class RequestProgress {
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
