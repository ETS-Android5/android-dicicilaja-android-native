package com.dicicilaja.app.BusinessReward.dataAPI.cabang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {
    @SerializedName("desa")
    @Expose
    private Object desa;
    @SerializedName("area")
    @Expose
    private Area area;

    public Object getDesa() {
        return desa;
    }

    public void setDesa(Object desa) {
        this.desa = desa;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
