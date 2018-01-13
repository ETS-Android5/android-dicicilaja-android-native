package id.variable.dicicilaja.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.variable.dicicilaja.Model.Token;

/**
 * Created by ziterz on 1/13/2018.
 */

public class Login {

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
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("_token")
    @Expose
    private Token token;

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
}
