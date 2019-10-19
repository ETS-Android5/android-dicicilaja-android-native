
package com.dicicilaja.app.OrderIn.Data.CabangRekomendasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("data")
    @Expose
    private Data___ data;
    @SerializedName("links")
    @Expose
    private Links______ links;

    public Data___ getData() {
        return data;
    }

    public void setData(Data___ data) {
        this.data = data;
    }

    public Links______ getLinks() {
        return links;
    }

    public void setLinks(Links______ links) {
        this.links = links;
    }

}
