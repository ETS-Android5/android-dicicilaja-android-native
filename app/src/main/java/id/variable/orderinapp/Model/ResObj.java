package id.variable.orderinapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 11/29/2017.
 */

public class ResObj {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("_token")
    @Expose
    private Token token;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
