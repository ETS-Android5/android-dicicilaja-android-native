package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemUbahMaxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class UbahMaxi {
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