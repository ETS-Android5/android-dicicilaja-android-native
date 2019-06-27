package com.dicicilaja.app.Activity.BusinessReward.dataAPI.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("profile_id")
    @Expose
    private Integer profileId;
    @SerializedName("point_reward")
    @Expose
    private Integer pointReward;
    @SerializedName("point_trip")
    @Expose
    private Object pointTrip;
    @SerializedName("insentif_mcy")
    @Expose
    private Object insentifMcy;
    @SerializedName("insentif_car")
    @Expose
    private Object insentifCar;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }

    public Object getPointTrip() {
        return pointTrip;
    }

    public void setPointTrip(Object pointTrip) {
        this.pointTrip = pointTrip;
    }

    public Object getInsentifMcy() {
        return insentifMcy;
    }

    public void setInsentifMcy(Object insentifMcy) {
        this.insentifMcy = insentifMcy;
    }

    public Object getInsentifCar() {
        return insentifCar;
    }

    public void setInsentifCar(Object insentifCar) {
        this.insentifCar = insentifCar;
    }
}
