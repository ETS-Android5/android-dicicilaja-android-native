
package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerMaxis {

    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("links")
    @Expose
    private Links_________ links;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Links_________ getLinks() {
        return links;
    }

    public void setLinks(Links_________ links) {
        this.links = links;
    }

}
