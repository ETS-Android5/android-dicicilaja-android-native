package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateAXI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateAXI {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
