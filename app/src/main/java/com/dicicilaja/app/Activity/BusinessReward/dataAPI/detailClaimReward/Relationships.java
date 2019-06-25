package com.dicicilaja.app.Activity.BusinessReward.dataAPI.detailClaimReward;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("product_catalog")
	private ProductCatalog productCatalog;

	@SerializedName("status")
	private Status status;

	public void setProductCatalog(ProductCatalog productCatalog){
		this.productCatalog = productCatalog;
	}

	public ProductCatalog getProductCatalog(){
		return productCatalog;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}
}