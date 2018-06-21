package com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemMarketplace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public class LoginObj {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("axi_id")
    @Expose
    private String axiId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("zipcode")
    @Expose
    private Object zipcode;
    @SerializedName("_token")
    @Expose
    private Token token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAxiId() {
        return axiId;
    }

    public void setAxiId(String axiId) {
        this.axiId = axiId;
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

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Object getZipcode() {
        return zipcode;
    }

    public void setZipcode(Object zipcode) {
        this.zipcode = zipcode;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
