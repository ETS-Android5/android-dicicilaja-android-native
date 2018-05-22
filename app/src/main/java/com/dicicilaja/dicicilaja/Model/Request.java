package com.dicicilaja.dicicilaja.Model;

/**
 * Created by Rienaldi on 09/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tracking_id")
    @Expose
    private Integer trackingId;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("colletral")
    @Expose
    private String colletral;
    @SerializedName("status")
    @Expose
    private String status;
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
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("hp")
    @Expose
    private String hp;
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("modified_by")
    @Expose
    private Object modifiedBy;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getColletral() {
        return colletral;
    }

    public void setColletral(String colletral) {
        this.colletral = colletral;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
