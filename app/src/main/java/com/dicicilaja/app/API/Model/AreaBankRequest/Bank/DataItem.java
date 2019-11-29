package com.dicicilaja.app.API.Model.AreaBankRequest.Bank;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("country")
	private String country;

	@SerializedName("bank_key")
	private String bankKey;

	@SerializedName("description")
	private String description;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setBankKey(String bankKey){
		this.bankKey = bankKey;
	}

	public String getBankKey(){
		return bankKey;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"country = '" + country + '\'' + 
			",bank_key = '" + bankKey + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}