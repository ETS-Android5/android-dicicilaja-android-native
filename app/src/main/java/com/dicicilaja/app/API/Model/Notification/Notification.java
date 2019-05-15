package com.dicicilaja.app.API.Model.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 2/8/2018.
 */

public class Notification {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

}
