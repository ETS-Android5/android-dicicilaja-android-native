package id.variable.dicicilaja.API.Item;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 30/12/2017.
 */

public class PengajuanResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Pengajuan> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pengajuan> getData() {
        return data;
    }

    public void setData(List<Pengajuan> data) {
        this.data = data;
    }

}
