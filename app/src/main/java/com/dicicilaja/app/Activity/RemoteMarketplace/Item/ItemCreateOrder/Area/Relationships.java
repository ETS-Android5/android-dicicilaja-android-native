
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemCreateOrder.Area;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("region")
    @Expose
    private Region region;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
