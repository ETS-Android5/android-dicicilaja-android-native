
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.KodeBank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accounts {

    @SerializedName("links")
    @Expose
    private Links_ links;

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

}
