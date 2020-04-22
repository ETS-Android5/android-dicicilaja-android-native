
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileCustomer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("province")
    @Expose
    private Object province;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("district")
    @Expose
    private Object district;
    @SerializedName("subdistrict")
    @Expose
    private Object subdistrict;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("bill_number")
    @Expose
    private String billNumber;
    @SerializedName("photo")
    @Expose
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getProvince() {
        return province;
    }

    public void setProvince(Object province) {
        this.province = province;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getDistrict() {
        return district;
    }

    public void setDistrict(Object district) {
        this.district = district;
    }

    public Object getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(Object subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
