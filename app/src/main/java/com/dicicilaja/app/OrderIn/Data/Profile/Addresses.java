
package com.dicicilaja.app.OrderIn.Data.Profile;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addresses {

    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("links")
    @Expose
    private Links links;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
