package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRekanBisnis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class InfoJaringan {
    @SerializedName("id_axi")
    @Expose
    private String idAxi;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("point_reward")
    @Expose
    private String pointReward;
    @SerializedName("point_trip")
    @Expose
    private String pointTrip;

    public String getIdAxi() {
        return idAxi;
    }

    public void setIdAxi(String idAxi) {
        this.idAxi = idAxi;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getPointReward() {
        return pointReward;
    }

    public void setPointReward(String pointReward) {
        this.pointReward = pointReward;
    }

    public String getPointTrip() {
        return pointTrip;
    }

    public void setPointTrip(String pointTrip) {
        this.pointTrip = pointTrip;
    }
}
