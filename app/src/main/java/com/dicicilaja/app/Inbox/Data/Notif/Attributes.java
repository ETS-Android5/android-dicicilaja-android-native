package com.dicicilaja.app.Inbox.Data.Notif;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("schedule")
	private Object schedule;

	@SerializedName("buttons")
	private Object buttons;

	@SerializedName("data")
	private Object data;

	@SerializedName("message")
	private String message;

	@SerializedName("url")
	private String url;

	public void setSchedule(Object schedule){
		this.schedule = schedule;
	}

	public Object getSchedule(){
		return schedule;
	}

	public void setButtons(Object buttons){
		this.buttons = buttons;
	}

	public Object getButtons(){
		return buttons;
	}

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}