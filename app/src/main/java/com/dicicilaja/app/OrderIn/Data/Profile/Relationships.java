
package com.dicicilaja.app.OrderIn.Data.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("addresses")
    @Expose
    private Addresses addresses;
    @SerializedName("detail-axis")
    @Expose
    private DetailAxis detailAxis;
    @SerializedName("partner-maxis")
    @Expose
    private PartnerMaxis partnerMaxis;

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public DetailAxis getDetailAxis() {
        return detailAxis;
    }

    public void setDetailAxis(DetailAxis detailAxis) {
        this.detailAxis = detailAxis;
    }

    public PartnerMaxis getPartnerMaxis() {
        return partnerMaxis;
    }

    public void setPartnerMaxis(PartnerMaxis partnerMaxis) {
        this.partnerMaxis = partnerMaxis;
    }

}
