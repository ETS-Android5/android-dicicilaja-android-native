
package com.dicicilaja.app.OrderIn.Data.CabangLainnya;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {

    @SerializedName("data")
    @Expose
    private Data__ data;
    @SerializedName("links")
    @Expose
    private Links____ links;

    public Data__ getData() {
        return data;
    }

    public void setData(Data__ data) {
        this.data = data;
    }

    public Links____ getLinks() {
        return links;
    }

    public void setLinks(Links____ links) {
        this.links = links;
    }

}
