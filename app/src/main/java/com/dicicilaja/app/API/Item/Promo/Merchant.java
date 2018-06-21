package com.dicicilaja.app.API.Item.Promo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 11/04/18.
 */

public class Merchant {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("handphone")
    @Expose
    private Object handphone;
    @SerializedName("telephone")
    @Expose
    private Object telephone;
    @SerializedName("ktp_number")
    @Expose
    private Object ktpNumber;
    @SerializedName("personal_npwp_number")
    @Expose
    private Object personalNpwpNumber;
    @SerializedName("company_npwp_number")
    @Expose
    private Object companyNpwpNumber;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("company_address")
    @Expose
    private Object companyAddress;
    @SerializedName("program_id")
    @Expose
    private Integer programId;
    @SerializedName("image_ktp")
    @Expose
    private Object imageKtp;
    @SerializedName("image_npwp")
    @Expose
    private Object imageNpwp;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getHandphone() {
        return handphone;
    }

    public void setHandphone(Object handphone) {
        this.handphone = handphone;
    }

    public Object getTelephone() {
        return telephone;
    }

    public void setTelephone(Object telephone) {
        this.telephone = telephone;
    }

    public Object getKtpNumber() {
        return ktpNumber;
    }

    public void setKtpNumber(Object ktpNumber) {
        this.ktpNumber = ktpNumber;
    }

    public Object getPersonalNpwpNumber() {
        return personalNpwpNumber;
    }

    public void setPersonalNpwpNumber(Object personalNpwpNumber) {
        this.personalNpwpNumber = personalNpwpNumber;
    }

    public Object getCompanyNpwpNumber() {
        return companyNpwpNumber;
    }

    public void setCompanyNpwpNumber(Object companyNpwpNumber) {
        this.companyNpwpNumber = companyNpwpNumber;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Object companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Object getImageKtp() {
        return imageKtp;
    }

    public void setImageKtp(Object imageKtp) {
        this.imageKtp = imageKtp;
    }

    public Object getImageNpwp() {
        return imageNpwp;
    }

    public void setImageNpwp(Object imageNpwp) {
        this.imageNpwp = imageNpwp;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}
