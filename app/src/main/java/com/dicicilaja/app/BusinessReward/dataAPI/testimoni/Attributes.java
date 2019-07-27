package com.dicicilaja.app.BusinessReward.dataAPI.testimoni;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes{

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

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public Integer getClaimRewardId() {
		return claimRewardId;
	}

	public void setClaimRewardId(Integer claimRewardId) {
		this.claimRewardId = claimRewardId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getTestimoni() {
		return testimoni;
	}

	public void setTestimoni(String testimoni) {
		this.testimoni = testimoni;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
}