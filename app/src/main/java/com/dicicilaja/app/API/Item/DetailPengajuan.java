package com.dicicilaja.app.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 1/14/2018.
 */

public class DetailPengajuan {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tracking")
    @Expose
    private String tracking;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("product")
    @Expose
    private Object product;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("sku")
    @Expose
    private Object sku;
    @SerializedName("specification")
    @Expose
    private Object specification;
    @SerializedName("colleteral")
    @Expose
    private String colleteral;
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
    @SerializedName("ammount")
    @Expose
    private String ammount;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
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
    @SerializedName("applicant")
    @Expose
    private Applicant applicant = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
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

    public String getColleteral() {
        return colleteral;
    }

    public void setColleteral(String colleteral) {
        this.colleteral = colleteral;
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

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getZipcode() { return zipcode; }

    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

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

    public Applicant getApplicant() { return applicant; }

    public void setApplicant(Applicant applicant) { this.applicant = applicant; }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
