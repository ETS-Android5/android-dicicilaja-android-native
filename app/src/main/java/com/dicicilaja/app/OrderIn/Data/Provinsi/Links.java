package com.dicicilaja.app.OrderIn.Data.Provinsi;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("next")
	private String next;

	@SerializedName("last")
	private String last;

	@SerializedName("first")
	private String first;

	@SerializedName("self")
	private String self;

	public void setNext(String next){
		this.next = next;
	}

	public String getNext(){
		return next;
	}

	public void setLast(String last){
		this.last = last;
	}

	public String getLast(){
		return last;
	}

	public void setFirst(String first){
		this.first = first;
	}

	public String getFirst(){
		return first;
	}

	public void setSelf(String self){
		this.self = self;
	}

	public String getSelf(){
		return self;
	}
}