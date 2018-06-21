package com.dicicilaja.app.API.Item.Colleteral;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fawazrifqi on 14/04/18.
 */

public class Colleteral {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose

    private List<Datum> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
