
package com.dicicilaja.app.Inbox.Data.Notif;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("buttons")
    @Expose
    private Object buttons;
    @SerializedName("schedule")
    @Expose
    private Object schedule;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("oneSignal_id")
    @Expose
    private String oneSignalId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getButtons() {
        return buttons;
    }

    public void setButtons(Object buttons) {
        this.buttons = buttons;
    }

    public Object getSchedule() {
        return schedule;
    }

    public void setSchedule(Object schedule) {
        this.schedule = schedule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOneSignalId() {
        return oneSignalId;
    }

    public void setOneSignalId(String oneSignalId) {
        this.oneSignalId = oneSignalId;
    }

}
