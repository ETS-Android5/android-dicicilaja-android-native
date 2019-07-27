package com.dicicilaja.app.BusinessReward.dataAPI.getClaimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships_ {
    @SerializedName("status")
    @Expose
    private Status_ status;
    @SerializedName("kategori")
    @Expose
    private Kategori kategori;
    @SerializedName("claim_reward")
    @Expose
    private ClaimReward claimReward;

    public Status_ getStatus() {
        return status;
    }

    public void setStatus(Status_ status) {
        this.status = status;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public ClaimReward getClaimReward() {
        return claimReward;
    }

    public void setClaimReward(ClaimReward claimReward) {
        this.claimReward = claimReward;
    }


}
