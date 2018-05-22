package com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.Item.ItemCreateRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 11/05/18.
 */

public class CreateRequest {
    @SerializedName("channel_id")
    @Expose
    private String channelId;
    @SerializedName("tracking_id")
    @Expose
    private String trackingId;
    @SerializedName("product")
    @Expose
    private Object product;
    @SerializedName("program_id")
    @Expose
    private String programId;
    @SerializedName("sku")
    @Expose
    private Object sku;
    @SerializedName("specification")
    @Expose
    private Object specification;
    @SerializedName("colleteral_id")
    @Expose
    private String colleteralId;
    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("tenor")
    @Expose
    private String tenor;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("area_id")
    @Expose
    private String areaId;
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
    @SerializedName("applicant_name")
    @Expose
    private String applicantName;
    @SerializedName("applicant_email")
    @Expose
    private String applicantEmail;
    @SerializedName("applicant_phone")
    @Expose
    private String applicantPhone;
    @SerializedName("aplicant_id")
    @Expose
    private String aplicantId;
    @SerializedName("applicant_district")
    @Expose
    private String applicantDistrict;
    @SerializedName("applicant_city")
    @Expose
    private String applicantCity;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
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

    public String getColleteralId() {
        return colleteralId;
    }

    public void setColleteralId(String colleteralId) {
        this.colleteralId = colleteralId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getAplicantId() {
        return aplicantId;
    }

    public void setAplicantId(String aplicantId) {
        this.aplicantId = aplicantId;
    }

    public String getApplicantDistrict() {
        return applicantDistrict;
    }

    public void setApplicantDistrict(String applicantDistrict) {
        this.applicantDistrict = applicantDistrict;
    }

    public String getApplicantCity() {
        return applicantCity;
    }

    public void setApplicantCity(String applicantCity) {
        this.applicantCity = applicantCity;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
