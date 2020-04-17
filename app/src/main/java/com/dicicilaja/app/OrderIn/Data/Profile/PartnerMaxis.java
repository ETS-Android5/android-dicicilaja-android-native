
package com.dicicilaja.app.OrderIn.Data.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerMaxis {

    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("links")
    @Expose
    private Links__ links;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Links__ getLinks() {
        return links;
    }

    public void setLinks(Links__ links) {
        this.links = links;
    }

}
