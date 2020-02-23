
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Branch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Village {

    @SerializedName("data")
    @Expose
    private Data_ data;
    @SerializedName("links")
    @Expose
    private Links__ links;

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

    public Links__ getLinks() {
        return links;
    }

    public void setLinks(Links__ links) {
        this.links = links;
    }

}
