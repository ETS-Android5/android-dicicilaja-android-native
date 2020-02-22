package com.dicicilaja.app.InformAXI.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Husni with ❤
 */

public class EditShProfile {

    @SerializedName("messege")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
