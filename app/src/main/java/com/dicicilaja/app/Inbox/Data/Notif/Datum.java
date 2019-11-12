
package com.dicicilaja.app.Inbox.Data.Notif;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("notifiable_id")
    @Expose
    private Integer notifiableId;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNotifiableId() {
        return notifiableId;
    }

    public void setNotifiableId(Integer notifiableId) {
        this.notifiableId = notifiableId;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
