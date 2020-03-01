package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemUbahCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UbahCustomer {
    @SerializedName("messege")
    @Expose
    private String messege;

    public String getMessage() {
        return messege;
    }

    public void setMessage(String message) {
        this.messege = message;
    }
}
