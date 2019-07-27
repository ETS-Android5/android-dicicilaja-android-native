package com.dicicilaja.app.BusinessReward.dataAPI.fotoKtpNpwp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("axi_id")
    @Expose
    private Integer axiId;
    @SerializedName("foto_ktp")
    @Expose
    private String fotoKtp;
    @SerializedName("foto_npwp")
    @Expose
    private String fotoNpwp;
    @SerializedName("no_ktp")
    @Expose
    private String noKtp;
    @SerializedName("no_npwp")
    @Expose
    private String noNpwp;

    public Integer getAxiId() {
        return axiId;
    }

    public void setAxiId(Integer axiId) {
        this.axiId = axiId;
    }

    public String getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(String fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

    public String getFotoNpwp() {
        return fotoNpwp;
    }

    public void setFotoNpwp(String fotoNpwp) {
        this.fotoNpwp = fotoNpwp;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoNpwp() {
        return noNpwp;
    }

    public void setNoNpwp(String noNpwp) {
        this.noNpwp = noNpwp;
    }
}
