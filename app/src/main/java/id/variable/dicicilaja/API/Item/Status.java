package id.variable.dicicilaja.API.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ziterz on 1/15/2018.
 */

public class Status {
    @SerializedName("responsible_person")
    @Expose
    private ResponsiblePerson responsiblePerson;
    @SerializedName("status")
    @Expose
    private String status;

    public ResponsiblePerson getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(ResponsiblePerson responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
