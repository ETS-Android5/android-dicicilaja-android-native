package id.variable.dicicilaja.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/25/2018.
 */

public class PengajuanProgressResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<PengajuanProgress> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PengajuanProgress> getData() {
        return data;
    }

    public void setData(List<PengajuanProgress> data) {
        this.data = data;
    }
}
