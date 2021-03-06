package com.dicicilaja.app.API.Model.RequestDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 28/04/18.
 */

public class Progress {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("responsible_person")
    @Expose
    private String responsiblePerson;
    @SerializedName("response_time")
    @Expose
    private String responseTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
}
