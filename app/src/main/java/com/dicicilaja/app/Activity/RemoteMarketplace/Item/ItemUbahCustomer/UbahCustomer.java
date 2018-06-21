package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UbahCustomer {
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
