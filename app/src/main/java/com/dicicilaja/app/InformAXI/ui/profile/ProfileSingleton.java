package com.dicicilaja.app.InformAXI.ui.profile;


import com.dicicilaja.app.InformAXI.model.AxiProfile;

/**
 * Created by Husni with ❤
 */

public class ProfileSingleton {

    private static final ProfileSingleton ourInstance = new ProfileSingleton();

    public static ProfileSingleton getInstance() {
        return ourInstance;
    }

    private ProfileSingleton() {
    }

    AxiProfile.Data.Profile profile;

    /* InformAxi */
    AxiProfile.Data.Benefit benefit;

    AxiProfile.Data.Lainnya other;

    public AxiProfile.Data.Profile getProfile() {
        return profile;
    }

    public void setProfile(AxiProfile.Data.Profile profile) {
        this.profile = profile;
    }

    public AxiProfile.Data.Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(AxiProfile.Data.Benefit benefit) {
        this.benefit = benefit;
    }

    public AxiProfile.Data.Lainnya getOther() {
        return other;
    }

    public void setOther(AxiProfile.Data.Lainnya other) {
        this.other = other;
    }

}
