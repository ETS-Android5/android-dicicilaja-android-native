package com.dicicilaja.dicicilaja.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rienaldi on 24/12/2017.
 */

public class Branch {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("sysbranch_id")
    @Expose
    private String sysbranch_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("zipcode")
    @Expose
    private String zipcode;

    public Integer getId() {
        return id;
    }

    public String getSysbranchId() {
        return sysbranch_id;
    }

    public String getName() {
        return name;
    }

    public String getZipcode() {
        return zipcode;
    }

}
