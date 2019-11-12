package com.dicicilaja.app.Inbox.Data.Notif;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("id")
	private String id;

	@SerializedName("notifiable_id")
	private int notifiableId;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNotifiableId(int notifiableId){
		this.notifiableId = notifiableId;
	}

	public int getNotifiableId(){
		return notifiableId;
	}
}