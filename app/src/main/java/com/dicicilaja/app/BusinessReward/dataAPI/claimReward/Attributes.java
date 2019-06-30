package com.dicicilaja.app.BusinessReward.dataAPI.claimReward;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("profile_id")
	@Expose
	private Integer profileId;
	@SerializedName("branch_id")
	@Expose
	private Integer branchId;
	@SerializedName("area_id")
	@Expose
	private Integer areaId;
	@SerializedName("crh_id")
	@Expose
	private Integer crhId;
	@SerializedName("product_catalog_id")
	@Expose
	private Integer productCatalogId;
	@SerializedName("status_id")
	@Expose
	private Integer statusId;
	@SerializedName("alamat")
	@Expose
	private String alamat;
	@SerializedName("no_resi")
	@Expose
	private String noResi;
	@SerializedName("no_po")
	@Expose
	private String noPo;
	@SerializedName("ongkos_kirim")
	@Expose
	private Integer ongkosKirim;

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getCrhId() {
		return crhId;
	}

	public void setCrhId(Integer crhId) {
		this.crhId = crhId;
	}

	public Integer getProductCatalogId() {
		return productCatalogId;
	}

	public void setProductCatalogId(Integer productCatalogId) {
		this.productCatalogId = productCatalogId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getNoResi() {
		return noResi;
	}

	public void setNoResi(String noResi) {
		this.noResi = noResi;
	}

	public String getNoPo() {
		return noPo;
	}

	public void setNoPo(String noPo) {
		this.noPo = noPo;
	}

	public Integer getOngkosKirim() {
		return ongkosKirim;
	}

	public void setOngkosKirim(Integer ongkosKirim) {
		this.ongkosKirim = ongkosKirim;
	}
}