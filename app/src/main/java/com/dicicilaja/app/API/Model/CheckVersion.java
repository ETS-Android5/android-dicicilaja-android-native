package com.dicicilaja.app.API.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckVersion {
    @SerializedName("data")
    @Expose
    private Integer data;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
