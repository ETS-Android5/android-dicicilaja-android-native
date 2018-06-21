package com.dicicilaja.app.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rienaldi on 18/01/2018.
 */

public class StatusDetail {

    @SerializedName("responsible_person")
    @Expose
    private ResponsiblePerson responsiblePerson;
    @SerializedName("status_name")
    @Expose
    private String statusName;
    @SerializedName("notes")
    @Expose
    private String notes;

    public ResponsiblePerson getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(ResponsiblePerson responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
