package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemRekanBisnis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fawazrifqi on 06/05/18.
 */

public class Data {
    @SerializedName("nama_mentor")
    @Expose
    private String namaMentor;
    @SerializedName("axi_refferal")
    @Expose
    private String axiRefferal;
    @SerializedName("point_reward")
    @Expose
    private String pointReward;
    @SerializedName("point_trip")
    @Expose
    private String pointTrip;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("info_jaringan")
    @Expose
    private List<InfoJaringan> infoJaringan = null;

    public String getNamaMentor() {
        return namaMentor;
    }

    public void setNamaMentor(String namaMentor) {
        this.namaMentor = namaMentor;
    }

    public String getAxiRefferal() {
        return axiRefferal;
    }

    public void setAxiRefferal(String axiRefferal) {
        this.axiRefferal = axiRefferal;
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

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public List<InfoJaringan> getInfoJaringan() {
        return infoJaringan;
    }

    public void setInfoJaringan(List<InfoJaringan> infoJaringan) {
        this.infoJaringan = infoJaringan;
    }

}
