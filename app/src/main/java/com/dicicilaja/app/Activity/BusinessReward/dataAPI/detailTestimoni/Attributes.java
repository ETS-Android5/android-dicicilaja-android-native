package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailTestimoni;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("profile_id")
	private int profileId;

	@SerializedName("testimoni")
	private String testimoni;

	@SerializedName("claim_reward_id")
	private int claimRewardId;

	@SerializedName("rating")
	private String rating;

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setProfileId(int profileId){
		this.profileId = profileId;
	}

	public int getProfileId(){
		return profileId;
	}

	public void setTestimoni(String testimoni){
		this.testimoni = testimoni;
	}

	public String getTestimoni(){
		return testimoni;
	}

	public void setClaimRewardId(int claimRewardId){
		this.claimRewardId = claimRewardId;
	}

	public int getClaimRewardId(){
		return claimRewardId;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}
}