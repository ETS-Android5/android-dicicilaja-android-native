package com.dicicilaja.app.BusinessReward.dataAPI.getDetailKategori;

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
    @SerializedName("relationships")
    @Expose
    private Relationships_ relationships;

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

    public Relationships_ getRelationships() {
        return relationships;
    }

    public void setRelationships(Relationships_ relationships) {
        this.relationships = relationships;
    }

}
