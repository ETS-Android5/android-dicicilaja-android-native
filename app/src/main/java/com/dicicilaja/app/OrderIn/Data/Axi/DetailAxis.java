
package com.dicicilaja.app.OrderIn.Data.Axi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailAxis {

    @SerializedName("data")
    @Expose
    private List<Datum___> data = null;
    @SerializedName("links")
    @Expose
    private Links________ links;

    public List<Datum___> getData() {
        return data;
    }

    public void setData(List<Datum___> data) {
        this.data = data;
    }

    public Links________ getLinks() {
        return links;
    }

    public void setLinks(Links________ links) {
        this.links = links;
    }

}
