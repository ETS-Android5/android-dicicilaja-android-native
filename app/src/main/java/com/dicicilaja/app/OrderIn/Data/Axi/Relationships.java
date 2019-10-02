package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("referrers")
	private Referrers referrers;

	@SerializedName("profiles")
	private Profiles profiles;

	@SerializedName("npwps")
	private Npwps npwps;

	@SerializedName("accounts")
	private Accounts accounts;

	@SerializedName("referees")
	private Referees referees;

	public void setReferrers(Referrers referrers){
		this.referrers = referrers;
	}

	public Referrers getReferrers(){
		return referrers;
	}

	public void setProfiles(Profiles profiles){
		this.profiles = profiles;
	}

	public Profiles getProfiles(){
		return profiles;
	}

	public void setNpwps(Npwps npwps){
		this.npwps = npwps;
	}

	public Npwps getNpwps(){
		return npwps;
	}

	public void setAccounts(Accounts accounts){
		this.accounts = accounts;
	}

	public Accounts getAccounts(){
		return accounts;
	}

	public void setReferees(Referees referees){
		this.referees = referees;
	}

	public Referees getReferees(){
		return referees;
	}
}