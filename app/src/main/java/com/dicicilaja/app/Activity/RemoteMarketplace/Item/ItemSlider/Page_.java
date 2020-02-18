
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page_ {

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
