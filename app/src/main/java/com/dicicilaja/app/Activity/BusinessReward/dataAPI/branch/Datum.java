package com.dicicilaja.app.Activity.BusinessReward.dataAPI.branch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("area_id")
    @Expose
    private String areaId;
    @SerializedName("sysbranch_id")
    @Expose
    private String sysbranchId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getSysbranchId() {
        return sysbranchId;
    }

    public void setSysbranchId(String sysbranchId) {
        this.sysbranchId = sysbranchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
