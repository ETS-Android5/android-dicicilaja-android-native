package com.dicicilaja.dicicilaja.API.Item.DetailRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ziterz on 1/29/2018.
 */

public class DetailRequest {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("progress")
    @Expose
    private List<Progress> progress = null;
    @SerializedName("survey_checklist")
    @Expose
    private List<SurveyChecklist> surveyChecklist = null;
    @SerializedName("responsible_crh")
    @Expose
    private String responsibleCrh;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }

    public List<SurveyChecklist> getSurveyChecklist() {
        return surveyChecklist;
    }

    public void setSurveyChecklist(List<SurveyChecklist> surveyChecklist) {
        this.surveyChecklist = surveyChecklist;
    }

    public String getResponsibleCrh() {
        return responsibleCrh;
    }

    public void setResponsibleCrh(String responsibleCrh) {
        this.responsibleCrh = responsibleCrh;
    }

}
