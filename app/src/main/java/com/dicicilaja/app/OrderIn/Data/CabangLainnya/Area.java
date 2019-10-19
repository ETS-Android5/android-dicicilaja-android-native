
package com.dicicilaja.app.OrderIn.Data.CabangLainnya;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Area {

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
