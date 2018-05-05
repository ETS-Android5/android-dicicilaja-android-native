package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahAxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 05/05/18.
 */

public class UbahAxi {
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
