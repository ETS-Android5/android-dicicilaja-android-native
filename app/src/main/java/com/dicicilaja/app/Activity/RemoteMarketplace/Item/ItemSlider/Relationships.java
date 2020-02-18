
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemSlider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("page")
    @Expose
    private Page_ page;

    public Page_ getPage() {
        return page;
    }

    public void setPage(Page_ page) {
        this.page = page;
    }

}
