package com.dicicilaja.app.InformAXI.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Husni with ❤
 */

public class Incentive {

    @SerializedName("data")
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("pribadi")
        private int pribadi = -1;
        @SerializedName("apresiasi")
        private int apresiasi = -1;
        @SerializedName("btl")
        private int btl = -1;
        @SerializedName("group")
        private int group = -1;
        @SerializedName("tahunan")
        private int tahunan = -1;
        @SerializedName("loyalti")
        private int loyalti = -1;
        @SerializedName("mentor")
        private int mentor = -1;

        public int getPribadi() {
            return pribadi;
        }

        public void setPribadi(int pribadi) {
            this.pribadi = pribadi;
        }

        public int getApresiasi() {
            return apresiasi;
        }

        public void setApresiasi(int apresiasi) {
            this.apresiasi = apresiasi;
        }

        public int getBtl() {
            return btl;
        }

        public void setBtl(int btl) {
            this.btl = btl;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public int getTahunan() {
            return tahunan;
        }

        public void setTahunan(int tahunan) {
            this.tahunan = tahunan;
        }

        public int getLoyalti() {
            return loyalti;
        }

        public void setLoyalti(int loyalti) {
            this.loyalti = loyalti;
        }

        public int getMentor() {
            return mentor;
        }

        public void setMentor(int mentor) {
            this.mentor = mentor;
        }
    }
}
