package com.dicicilaja.app.OrderIn.Data.Pekerjaan;

import com.google.gson.annotations.SerializedName;


public class Response {

	@SerializedName("jobName")
	private String jobName;

	@SerializedName("updateDate")
	private Object updateDate;

	@SerializedName("createBy")
	private String createBy;

	@SerializedName("updateBy")
	private Object updateBy;

	@SerializedName("insertDate")
	private String insertDate;

	@SerializedName("jobCode")
	private String jobCode;

	@SerializedName("jobDescription")
	private String jobDescription;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public String getJobName(){
		return jobName;
	}

	public void setUpdateDate(Object updateDate){
		this.updateDate = updateDate;
	}

	public Object getUpdateDate(){
		return updateDate;
	}

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
		return createBy;
	}

	public void setUpdateBy(Object updateBy){
		this.updateBy = updateBy;
	}

	public Object getUpdateBy(){
		return updateBy;
	}

	public void setInsertDate(String insertDate){
		this.insertDate = insertDate;
	}

	public String getInsertDate(){
		return insertDate;
	}

	public void setJobCode(String jobCode){
		this.jobCode = jobCode;
	}

	public String getJobCode(){
		return jobCode;
	}

	public void setJobDescription(String jobDescription){
		this.jobDescription = jobDescription;
	}

	public String getJobDescription(){
		return jobDescription;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}