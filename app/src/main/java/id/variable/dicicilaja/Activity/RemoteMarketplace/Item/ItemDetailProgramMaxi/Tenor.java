package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public class Tenor {
    @SerializedName("bulan")
    @Expose
    private String bulan;
    @SerializedName("cicilan")
    @Expose
    private String cicilan;

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getCicilan() {
        return cicilan;
    }

    public void setCicilan(String cicilan) {
        this.cicilan = cicilan;
    }
}
