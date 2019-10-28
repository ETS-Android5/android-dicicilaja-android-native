package com.dicicilaja.app.OrderIn.Data.Axi;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("nomor_axi_id")
	private String nomorAxiId;

	@SerializedName("updated-at")
	private String updatedAt;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("npwp_id")
	private int npwpId;

	@SerializedName("axi_id_reff")
	private int axiIdReff;

	@SerializedName("cabang_daftar")
	private int cabangDaftar;

	@SerializedName("status_id")
	private int statusId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("cabang_id")
	private int cabangId;

	@SerializedName("profile_id")
	private int profileId;

	@SerializedName("created-at")
	private String createdAt;

	@SerializedName("nomor_tagihan")
	private String nomorTagihan;

	@SerializedName("tanggal_daftar")
	private String tanggalDaftar;

	public void setNomorAxiId(String nomorAxiId){
		this.nomorAxiId = nomorAxiId;
	}

	public String getNomorAxiId(){
		return nomorAxiId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setNpwpId(int npwpId){
		this.npwpId = npwpId;
	}

	public int getNpwpId(){
		return npwpId;
	}

	public void setAxiIdReff(int axiIdReff){
		this.axiIdReff = axiIdReff;
	}

	public int getAxiIdReff(){
		return axiIdReff;
	}

	public void setCabangDaftar(int cabangDaftar){
		this.cabangDaftar = cabangDaftar;
	}

	public int getCabangDaftar(){
		return cabangDaftar;
	}

	public void setStatusId(int statusId){
		this.statusId = statusId;
	}

	public int getStatusId(){
		return statusId;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setCabangId(int cabangId){
		this.cabangId = cabangId;
	}

	public int getCabangId(){
		return cabangId;
	}

	public void setProfileId(int profileId){
		this.profileId = profileId;
	}

	public int getProfileId(){
		return profileId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNomorTagihan(String nomorTagihan){
		this.nomorTagihan = nomorTagihan;
	}

	public String getNomorTagihan(){
		return nomorTagihan;
	}

	public void setTanggalDaftar(String tanggalDaftar){
		this.tanggalDaftar = tanggalDaftar;
	}

	public String getTanggalDaftar(){
		return tanggalDaftar;
	}
}