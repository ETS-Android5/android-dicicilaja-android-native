package com.dicicilaja.app.Activity.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("product_catalog")
    @Expose
    private ProductCatalog productCatalog;
    @SerializedName("testimonis")
    @Expose
    private Testimonis testimonis;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Testimonis getTestimonis() {
        return testimonis;
    }

    public void setTestimonis(Testimonis testimonis) {
        this.testimonis = testimonis;
    }

}
