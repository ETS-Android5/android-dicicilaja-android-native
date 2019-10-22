
package com.dicicilaja.app.OrderIn.Data.Axi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addresses {

    @SerializedName("data")
    @Expose
    private List<Datum__> data = null;
    @SerializedName("links")
    @Expose
    private Links_______ links;

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

    public Links_______ getLinks() {
        return links;
    }

    public void setLinks(Links_______ links) {
        this.links = links;
    }

}
