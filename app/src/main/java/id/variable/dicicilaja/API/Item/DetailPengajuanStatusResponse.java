package id.variable.dicicilaja.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/15/2018.
 */

public class DetailPengajuanStatusResponse {
    @SerializedName("transaction")
    @Expose
    private Transaction transaction;
    @SerializedName("status")
    @Expose
    private List<Status> status = null;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}
