package com.dicicilaja.app.Inbox.Data.Popup;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("image")
	private String image;

	@SerializedName("role")
	private String role;

	@SerializedName("text")
	private String text;

	@SerializedName("url")
	private String url;

	@SerializedName("status")
	private int status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}