
package com.dicicilaja.app.BFF.API.Data.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("user_id_onesignal")
    @Expose
    private Integer userIdOnesignal;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("_token")
    @Expose
    private Token token;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("popup_url")
    @Expose
    private String popupUrl;
    @SerializedName("popup_text")
    @Expose
    private String popupText;
    @SerializedName("popup_image")
    @Expose
    private String popupImage;

    public Integer getUserIdOnesignal() {
        return userIdOnesignal;
    }

    public void setUserIdOnesignal(Integer userIdOnesignal) {
        this.userIdOnesignal = userIdOnesignal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPopupUrl() {
        return popupUrl;
    }

    public void setPopupUrl(String popupUrl) {
        this.popupUrl = popupUrl;
    }

    public String getPopupText() {
        return popupText;
    }

    public void setPopupText(String popupText) {
        this.popupText = popupText;
    }

    public String getPopupImage() {
        return popupImage;
    }

    public void setPopupImage(String popupImage) {
        this.popupImage = popupImage;
    }

}
