package com.dicicilaja.app.Activity.BusinessReward.dataAPI.point;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Included {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("attributes")
    @Expose
    private Attributes_ attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Attributes_ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes_ attributes) {
        this.attributes = attributes;
    }

}
