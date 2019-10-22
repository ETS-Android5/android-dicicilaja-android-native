
package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("accounts")
    @Expose
    private Accounts accounts;
    @SerializedName("profiles")
    @Expose
    private Profiles profiles;
    @SerializedName("npwps")
    @Expose
    private Npwps npwps;
    @SerializedName("referrers")
    @Expose
    private Referrers referrers;
    @SerializedName("referees")
    @Expose
    private Referees referees;

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Profiles getProfiles() {
        return profiles;
    }

    public void setProfiles(Profiles profiles) {
        this.profiles = profiles;
    }

    public Npwps getNpwps() {
        return npwps;
    }

    public void setNpwps(Npwps npwps) {
        this.npwps = npwps;
    }

    public Referrers getReferrers() {
        return referrers;
    }

    public void setReferrers(Referrers referrers) {
        this.referrers = referrers;
    }

    public Referees getReferees() {
        return referees;
    }

    public void setReferees(Referees referees) {
        this.referees = referees;
    }

}
