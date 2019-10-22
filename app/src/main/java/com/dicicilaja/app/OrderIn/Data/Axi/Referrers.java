
package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Referrers {

    @SerializedName("data")
    @Expose
    private Data___ data;
    @SerializedName("links")
    @Expose
    private Links____ links;

    public Data___ getData() {
        return data;
    }

    public void setData(Data___ data) {
        this.data = data;
    }

    public Links____ getLinks() {
        return links;
    }

    public void setLinks(Links____ links) {
        this.links = links;
    }

}
