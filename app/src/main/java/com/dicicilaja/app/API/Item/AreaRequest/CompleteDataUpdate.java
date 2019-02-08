package com.dicicilaja.app.API.Item.AreaRequest;

import com.google.gson.annotations.SerializedName;

public class CompleteDataUpdate {
    @SerializedName("data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
