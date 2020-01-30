package com.dicicilaja.app.BusinessReward.dataAPI.claimReward;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Husni with ❤
 */

public class PostClaimReward {

    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
