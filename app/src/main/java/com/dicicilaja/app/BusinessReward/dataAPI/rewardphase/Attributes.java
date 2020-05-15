
package com.dicicilaja.app.BusinessReward.dataAPI.rewardphase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created-at")
    @Expose
    private Object createdAt;
    @SerializedName("updated-at")
    @Expose
    private String updatedAt;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
