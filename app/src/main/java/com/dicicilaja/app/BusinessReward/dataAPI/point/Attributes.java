package com.dicicilaja.app.BusinessReward.dataAPI.point;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("insentif_mcy")
	private int insentifMcy;

	@SerializedName("insentif_car")
	private int insentifCar;

	@SerializedName("profile_id")
	private String profileId;

	@SerializedName("point_trip")
	private int pointTrip;

	@SerializedName("point_reward")
	private int pointReward;

	public void setInsentifMcy(int insentifMcy){
		this.insentifMcy = insentifMcy;
	}

	public int getInsentifMcy(){
		return insentifMcy;
	}

	public void setInsentifCar(int insentifCar){
		this.insentifCar = insentifCar;
	}

	public int getInsentifCar(){
		return insentifCar;
	}

	public void setProfileId(String profileId){
		this.profileId = profileId;
	}

	public String getProfileId(){
		return profileId;
	}

	public void setPointTrip(int pointTrip){
		this.pointTrip = pointTrip;
	}

	public int getPointTrip(){
		return pointTrip;
	}

	public void setPointReward(int pointReward){
		this.pointReward = pointReward;
	}

	public int getPointReward(){
		return pointReward;
	}
}