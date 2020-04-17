
package com.dicicilaja.app.OrderIn.Data.Transaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jaminan {

    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("links")
    @Expose
    private Links links;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
