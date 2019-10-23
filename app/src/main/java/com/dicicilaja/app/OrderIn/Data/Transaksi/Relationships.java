
package com.dicicilaja.app.OrderIn.Data.Transaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("transaksi_details")
    @Expose
    private TransaksiDetails transaksiDetails;
    @SerializedName("voucher-code")
    @Expose
    private VoucherCode voucherCode;

    public TransaksiDetails getTransaksiDetails() {
        return transaksiDetails;
    }

    public void setTransaksiDetails(TransaksiDetails transaksiDetails) {
        this.transaksiDetails = transaksiDetails;
    }

    public VoucherCode getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(VoucherCode voucherCode) {
        this.voucherCode = voucherCode;
    }

}
