package com.dicicilaja.app.NewSimulation.data.TipeObjek;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("path")
	private String path;

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("last_page")
	private int lastPage;

	@SerializedName("from")
	private int from;

	@SerializedName("to")
	private int to;

	@SerializedName("current_page")
	private int currentPage;

	public Meta(String path, int perPage, int total, int lastPage, int from, int to, int currentPage) {
		this.path = path;
		this.perPage = perPage;
		this.total = total;
		this.lastPage = lastPage;
		this.from = from;
		this.to = to;
		this.currentPage = currentPage;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setLastPage(int lastPage){
		this.lastPage = lastPage;
	}

	public int getLastPage(){
		return lastPage;
	}

	public void setFrom(int from){
		this.from = from;
	}

	public int getFrom(){
		return from;
	}

	public void setTo(int to){
		this.to = to;
	}

	public int getTo(){
		return to;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}
}