package com.dicicilaja.app.InformAXI.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husni with ❤
 */

public class AxiRegister {

    @SerializedName("data")
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("id")
        private String id;
        @SerializedName("date")
        private String date;
        @SerializedName("count")
        private int count;
        @SerializedName("link")
        private String link;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
