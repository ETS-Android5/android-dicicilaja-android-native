package com.dicicilaja.app.OrderIn.Data.CabangRekomendasi;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("page")
	private Page page;

	public void setPage(Page page){
		this.page = page;
	}

	public Page getPage(){
		return page;
	}
}