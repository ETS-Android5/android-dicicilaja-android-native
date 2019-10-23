
package com.dicicilaja.app.OrderIn.Data.Transaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherCode {

    @SerializedName("data")
    @Expose
    private Data_ data;
    @SerializedName("links")
    @Expose
    private Links_ links;

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

}
