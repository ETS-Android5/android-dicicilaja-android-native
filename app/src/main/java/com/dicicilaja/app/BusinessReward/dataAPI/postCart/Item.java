
package com.dicicilaja.app.BusinessReward.dataAPI.postCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("counts")
    @Expose
    private Integer counts;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

}
