
package com.dicicilaja.app.OrderIn.Data.CabangTerdekat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data___ {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
