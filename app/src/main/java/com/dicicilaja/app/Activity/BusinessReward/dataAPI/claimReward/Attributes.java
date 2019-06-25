package com.dicicilaja.app.Activity.BusinessReward.dataAPI.claimReward;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("no_resi")
	private String noResi;

	@SerializedName("product_catalog_id")
	private int productCatalogId;

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("ongkos_kirim")
	private int ongkosKirim;

	@SerializedName("branch_id")
	private int branchId;

	@SerializedName("profile_id")
	private int profileId;

	@SerializedName("crh_id")
	private int crhId;

	@SerializedName("area_id")
	private int areaId;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("no_po")
	private String noPo;

	public void setNoResi(String noResi){
		this.noResi = noResi;
	}

	public String getNoResi(){
		return noResi;
	}

	public void setProductCatalogId(int productCatalogId){
		this.productCatalogId = productCatalogId;
	}

	public int getProductCatalogId(){
		return productCatalogId;
	}

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setOngkosKirim(int ongkosKirim){
		this.ongkosKirim = ongkosKirim;
	}

	public int getOngkosKirim(){
		return ongkosKirim;
	}

	public void setBranchId(int branchId){
		this.branchId = branchId;
	}

	public int getBranchId(){
		return branchId;
	}

	public void setProfileId(int profileId){
		this.profileId = profileId;
	}

	public int getProfileId(){
		return profileId;
	}

	public void setCrhId(int crhId){
		this.crhId = crhId;
	}

	public int getCrhId(){
		return crhId;
	}

	public void setAreaId(int areaId){
		this.areaId = areaId;
	}

	public int getAreaId(){
		return areaId;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNoPo(String noPo){
		this.noPo = noPo;
	}

	public String getNoPo(){
		return noPo;
	}
}