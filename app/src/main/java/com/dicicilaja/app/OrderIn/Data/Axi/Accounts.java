
package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accounts {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("links")
    @Expose
    private Links_ links;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

}
