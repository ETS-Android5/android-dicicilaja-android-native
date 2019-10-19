
package com.dicicilaja.app.OrderIn.Data.CabangTerdekat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("data")
    @Expose
    private Data____ data;
    @SerializedName("links")
    @Expose
    private Links________ links;

    public Data____ getData() {
        return data;
    }

    public void setData(Data____ data) {
        this.data = data;
    }

    public Links________ getLinks() {
        return links;
    }

    public void setLinks(Links________ links) {
        this.links = links;
    }

}
