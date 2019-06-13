package com.dicicilaja.app.NewSimulation.data.hitungsimulasi;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("informasi_jaminan")
	private InformasiJaminan informasiJaminan;

	@SerializedName("hasil_simulasi")
	private HasilSimulasi hasilSimulasi;

	public void setInformasiJaminan(InformasiJaminan informasiJaminan){
		this.informasiJaminan = informasiJaminan;
	}

	public InformasiJaminan getInformasiJaminan(){
		return informasiJaminan;
	}

	public void setHasilSimulasi(HasilSimulasi hasilSimulasi){
		this.hasilSimulasi = hasilSimulasi;
	}

	public HasilSimulasi getHasilSimulasi(){
		return hasilSimulasi;
	}
}