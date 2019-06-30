package com.dicicilaja.app.BusinessReward.dataAPI.testimoni;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("claim_reward")
	private ClaimReward claimReward;

	@SerializedName("status")
	private Status status;

	public void setClaimReward(ClaimReward claimReward){
		this.claimReward = claimReward;
	}

	public ClaimReward getClaimReward(){
		return claimReward;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}
}