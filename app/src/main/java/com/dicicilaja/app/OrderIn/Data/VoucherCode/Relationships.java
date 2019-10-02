package com.dicicilaja.app.OrderIn.Data.VoucherCode;

import com.google.gson.annotations.SerializedName;

public class Relationships{

	@SerializedName("transaksi")
	private Transaksi transaksi;

	public void setTransaksi(Transaksi transaksi){
		this.transaksi = transaksi;
	}

	public Transaksi getTransaksi(){
		return transaksi;
	}
}