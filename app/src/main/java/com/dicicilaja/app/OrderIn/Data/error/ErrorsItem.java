package com.dicicilaja.app.OrderIn.Data.error;

import com.google.gson.annotations.SerializedName;

public class ErrorsItem{

	@SerializedName("detail")
	private String detail;

	@SerializedName("title")
	private String title;

	@SerializedName("status")
	private String status;

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}