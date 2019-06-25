package com.dicicilaja.app.Activity.BusinessReward.dataAPI.kategori;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("product_catalogs")
	@Expose
	private ProductCatalogs productCatalogs;

	public ProductCatalogs getProductCatalogs() {
		return productCatalogs;
	}

	public void setProductCatalogs(ProductCatalogs productCatalogs) {
		this.productCatalogs = productCatalogs;
	}
}