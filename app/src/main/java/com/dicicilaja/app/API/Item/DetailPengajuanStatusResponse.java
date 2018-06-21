package com.dicicilaja.app.API.Item;

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
    @SerializedName("status_detail")
    @Expose
    private List<StatusDetail> statusDetail = null;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<StatusDetail> getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(List<StatusDetail> statusDetail) {
        this.statusDetail = statusDetail;
    }
}
