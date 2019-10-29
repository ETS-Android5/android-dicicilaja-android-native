package com.dicicilaja.app.OrderIn.Data.error;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Error{

	@SerializedName("errors")
	private List<ErrorsItem> errors;

	public void setErrors(List<ErrorsItem> errors){
		this.errors = errors;
	}

	public List<ErrorsItem> getErrors(){
		return errors;
	}
}