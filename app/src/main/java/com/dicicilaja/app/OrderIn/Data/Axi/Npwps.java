
package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Npwps {

    @SerializedName("data")
    @Expose
    private Data__ data;
    @SerializedName("links")
    @Expose
    private Links___ links;

    public Data__ getData() {
        return data;
    }

    public void setData(Data__ data) {
        this.data = data;
    }

    public Links___ getLinks() {
        return links;
    }

    public void setLinks(Links___ links) {
        this.links = links;
    }

}
