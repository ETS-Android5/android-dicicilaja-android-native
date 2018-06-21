package com.dicicilaja.app.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 1/15/2018.
 */

public class Transaction {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tracking_id")
    @Expose
    private Integer trackingId;
    @SerializedName("channel_id")
    @Expose
    private Integer channelId;
    @SerializedName("product")
    @Expose
    private Object product;
    @SerializedName("program_id")
    @Expose
    private Integer programId;
    @SerializedName("sku")
    @Expose
    private Object sku;
    @SerializedName("specification")
    @Expose
    private Object specification;
    @SerializedName("colleteral_id")
    @Expose
    private Integer colleteralId;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("tenor")
    @Expose
    private Integer tenor;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("area_id")
    @Expose
    private Integer areaId;
    @SerializedName("branch_id")
    @Expose
    private Integer branchId;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("hp")
    @Expose
    private String hp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("ktp_image")
    @Expose
    private String ktpImage;
    @SerializedName("colleteral_image")
    @Expose
    private String colleteralImage;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("modified_by")
    @Expose
    private Object modifiedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Object getSku() {
        return sku;
    }

    public void setSku(Object sku) {
        this.sku = sku;
    }

    public Object getSpecification() {
        return specification;
    }

    public void setSpecification(Object specification) {
        this.specification = specification;
    }

    public Integer getColleteralId() {
        return colleteralId;
    }

    public void setColleteralId(Integer colleteralId) {
        this.colleteralId = colleteralId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTenor() {
        return tenor;
    }

    public void setTenor(Integer tenor) {
        this.tenor = tenor;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getKtpImage() {
        return ktpImage;
    }

    public void setKtpImage(String ktpImage) {
        this.ktpImage = ktpImage;
    }

    public String getColleteralImage() {
        return colleteralImage;
    }

    public void setColleteralImage(String colleteralImage) {
        this.colleteralImage = colleteralImage;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Object getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}
