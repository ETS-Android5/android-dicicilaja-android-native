package com.dicicilaja.app.NewSimulation.data.hitungsimulasi;

import com.google.gson.annotations.SerializedName;

public class HasilSimulasi{

	@SerializedName("administrasi")
	private String administrasi;

	@SerializedName("rate_premi_ass_credit")
	private String ratePremiAssCredit;

	@SerializedName("provisi")
	private String provisi;

	@SerializedName("dana_diterima")
	private String danaDiterima;

	@SerializedName("flat_rate")
	private String flatRate;

	@SerializedName("rate_premi_ass_cash")
	private String ratePremiAssCash;

	@SerializedName("total_pembayaran_pertama")
	private String totalPembayaranPertama;

	@SerializedName("dp")
	private String dp;

	@SerializedName("premi_ass_credit")
	private String premiAssCredit;

	@SerializedName("personal_accident")
	private String personalAccident;

	@SerializedName("rate_bunga")
	private String rateBunga;

	@SerializedName("bunga")
	private String bunga;

	@SerializedName("ltv")
	private String ltv;

	@SerializedName("premi_ass_cash")
	private String premiAssCash;

	@SerializedName("rate_personal_accident")
	private String ratePersonalAccident;

	@SerializedName("nilai_otr")
	private String nilaiOtr;

	@SerializedName("total_angsuran")
	private String totalAngsuran;

	@SerializedName("effective_rate")
	private String effectiveRate;

	@SerializedName("angsuran_per_bulan")
	private String angsuranPerBulan;

	@SerializedName("total_pokok_hutang")
	private String totalPokokHutang;

	public void setAdministrasi(String administrasi){
		this.administrasi = administrasi;
	}

	public String getAdministrasi(){
		return administrasi;
	}

	public void setRatePremiAssCredit(String ratePremiAssCredit){
		this.ratePremiAssCredit = ratePremiAssCredit;
	}

	public String getRatePremiAssCredit(){
		return ratePremiAssCredit;
	}

	public void setProvisi(String provisi){
		this.provisi = provisi;
	}

	public String getProvisi(){
		return provisi;
	}

	public void setDanaDiterima(String danaDiterima){
		this.danaDiterima = danaDiterima;
	}

	public String getDanaDiterima(){
		return danaDiterima;
	}

	public void setFlatRate(String flatRate){
		this.flatRate = flatRate;
	}

	public String getFlatRate(){
		return flatRate;
	}

	public void setRatePremiAssCash(String ratePremiAssCash){
		this.ratePremiAssCash = ratePremiAssCash;
	}

	public String getRatePremiAssCash(){
		return ratePremiAssCash;
	}

	public void setTotalPembayaranPertama(String totalPembayaranPertama){
		this.totalPembayaranPertama = totalPembayaranPertama;
	}

	public String getTotalPembayaranPertama(){
		return totalPembayaranPertama;
	}

	public void setDp(String dp){
		this.dp = dp;
	}

	public String getDp(){
		return dp;
	}

	public void setPremiAssCredit(String premiAssCredit){
		this.premiAssCredit = premiAssCredit;
	}

	public String getPremiAssCredit(){
		return premiAssCredit;
	}

	public void setPersonalAccident(String personalAccident){
		this.personalAccident = personalAccident;
	}

	public String getPersonalAccident(){
		return personalAccident;
	}

	public void setRateBunga(String rateBunga){
		this.rateBunga = rateBunga;
	}

	public String getRateBunga(){
		return rateBunga;
	}

	public void setBunga(String bunga){
		this.bunga = bunga;
	}

	public String getBunga(){
		return bunga;
	}

	public void setLtv(String ltv){
		this.ltv = ltv;
	}

	public String getLtv(){
		return ltv;
	}

	public void setPremiAssCash(String premiAssCash){
		this.premiAssCash = premiAssCash;
	}

	public String getPremiAssCash(){
		return premiAssCash;
	}

	public void setRatePersonalAccident(String ratePersonalAccident){
		this.ratePersonalAccident = ratePersonalAccident;
	}

	public String getRatePersonalAccident(){
		return ratePersonalAccident;
	}

	public void setNilaiOtr(String nilaiOtr){
		this.nilaiOtr = nilaiOtr;
	}

	public String getNilaiOtr(){
		return nilaiOtr;
	}

	public void setTotalAngsuran(String totalAngsuran){
		this.totalAngsuran = totalAngsuran;
	}

	public String getTotalAngsuran(){
		return totalAngsuran;
	}

	public void setEffectiveRate(String effectiveRate){
		this.effectiveRate = effectiveRate;
	}

	public String getEffectiveRate(){
		return effectiveRate;
	}

	public void setAngsuranPerBulan(String angsuranPerBulan){
		this.angsuranPerBulan = angsuranPerBulan;
	}

	public String getAngsuranPerBulan(){
		return angsuranPerBulan;
	}

	public void setTotalPokokHutang(String totalPokokHutang){
		this.totalPokokHutang = totalPokokHutang;
	}

	public String getTotalPokokHutang(){
		return totalPokokHutang;
	}
}