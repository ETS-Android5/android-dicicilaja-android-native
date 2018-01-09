package id.variable.dicicilaja.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/9/2018.
 */

public class DatabaseEmployeeResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<DatabaseEmployee> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DatabaseEmployee> getData() {
        return data;
    }

    public void setData(List<DatabaseEmployee> data) {
        this.data = data;
    }

}