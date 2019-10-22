
package com.dicicilaja.app.OrderIn.Data.Axi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Referees {

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;
    @SerializedName("links")
    @Expose
    private Links_____ links;

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public Links_____ getLinks() {
        return links;
    }

    public void setLinks(Links_____ links) {
        this.links = links;
    }

}
