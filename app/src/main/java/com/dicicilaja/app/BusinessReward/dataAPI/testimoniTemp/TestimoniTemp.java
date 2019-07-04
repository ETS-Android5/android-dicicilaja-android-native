package com.dicicilaja.app.BusinessReward.dataAPI.testimoniTemp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestimoniTemp {
    @SerializedName("profile_id")
    @Expose
    private Integer profileId;
    @SerializedName("claim_reward_id")
    @Expose
    private Integer claimRewardId;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("testimoni")
    @Expose
    private String testimoni;
    @SerializedName("rating")
    @Expose
    private float rating;

    public TestimoniTemp(Integer profileId, Integer claimRewardId, Integer statusId, String testimoni, float rating) {
        this.profileId = profileId;
        this.claimRewardId = claimRewardId;
        this.statusId = statusId;
        this.testimoni = testimoni;
        this.rating = rating;
    }
}
