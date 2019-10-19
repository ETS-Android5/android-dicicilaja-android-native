
package com.dicicilaja.app.OrderIn.Data.CabangTerdekat;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branches {

    @SerializedName("data")
    @Expose
    private List<Datum___> data = null;
    @SerializedName("links")
    @Expose
    private Links_________ links;

    public List<Datum___> getData() {
        return data;
    }

    public void setData(List<Datum___> data) {
        this.data = data;
    }

    public Links_________ getLinks() {
        return links;
    }

    public void setLinks(Links_________ links) {
        this.links = links;
    }

}
