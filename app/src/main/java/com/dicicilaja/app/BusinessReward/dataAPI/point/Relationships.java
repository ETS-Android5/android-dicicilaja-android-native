package com.dicicilaja.app.BusinessReward.dataAPI.point;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("semester")
	private Semester semester;

	public void setSemester(Semester semester){
		this.semester = semester;
	}

	public Semester getSemester(){
		return semester;
	}
}