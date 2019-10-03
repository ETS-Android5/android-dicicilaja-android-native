package com.dicicilaja.app.OrderIn.Data.CabangRekomendasi;

import com.google.gson.annotations.SerializedName;

public class Page{

	@SerializedName("per-page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("current-page")
	private int currentPage;

	@SerializedName("last-page")
	private int lastPage;

	@SerializedName("from")
	private int from;

	@SerializedName("to")
	private int to;

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

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
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
}