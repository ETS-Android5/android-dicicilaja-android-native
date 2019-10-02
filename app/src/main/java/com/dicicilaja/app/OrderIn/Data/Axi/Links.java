package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("last")
	private String last;

	@SerializedName("first")
	private String first;

	@SerializedName("self")
	private String self;

	@SerializedName("related")
	private String related;

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

	public void setRelated(String related){
		this.related = related;
	}

	public String getRelated(){
		return related;
	}
}