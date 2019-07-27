package com.dicicilaja.app.BusinessReward.dataAPI.getTestimoni;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("profile_id")
    @Expose
    private Integer profileId;
    @SerializedName("testimoni")
    @Expose
    private String testimoni;
    @SerializedName("rating")
    @Expose
    private Double rating;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getTestimoni() {
        return testimoni;
    }

    public void setTestimoni(String testimoni) {
        this.testimoni = testimoni;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
