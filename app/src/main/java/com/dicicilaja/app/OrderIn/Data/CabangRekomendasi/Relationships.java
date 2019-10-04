
package com.dicicilaja.app.OrderIn.Data.CabangRekomendasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("village")
    @Expose
    private Village village;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

}
