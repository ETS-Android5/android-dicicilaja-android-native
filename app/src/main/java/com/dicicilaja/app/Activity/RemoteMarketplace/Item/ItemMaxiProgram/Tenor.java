
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMaxiProgram;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tenor {

    @SerializedName("bulan")
    @Expose
    private String bulan;
    @SerializedName("cicilan")
    @Expose
    private String cicilan;

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getCicilan() {
        return cicilan;
    }

    public void setCicilan(String cicilan) {
        this.cicilan = cicilan;
    }

}
