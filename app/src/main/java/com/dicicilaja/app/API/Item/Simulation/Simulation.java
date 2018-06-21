package com.dicicilaja.app.API.Item.Simulation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 17/04/18.
 */

public class Simulation {
    @SerializedName("installment_amount")
    @Expose
    private Integer installmentAmount;

    public Integer getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        this.installmentAmount = installmentAmount;
    }
}
