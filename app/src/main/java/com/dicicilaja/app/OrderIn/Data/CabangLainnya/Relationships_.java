
package com.dicicilaja.app.OrderIn.Data.CabangLainnya;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships_ {

    @SerializedName("province")
    @Expose
    private Province province;
    @SerializedName("districts")
    @Expose
    private Districts districts;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("villages")
    @Expose
    private Villages villages;
    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("branches")
    @Expose
    private Branches branches;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Districts getDistricts() {
        return districts;
    }

    public void setDistricts(Districts districts) {
        this.districts = districts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Villages getVillages() {
        return villages;
    }

    public void setVillages(Villages villages) {
        this.villages = villages;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Branches getBranches() {
        return branches;
    }

    public void setBranches(Branches branches) {
        this.branches = branches;
    }

}
