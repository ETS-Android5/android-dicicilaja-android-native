package com.dicicilaja.app.BusinessReward.dataAPI.rewardPhase;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Husni with ❤
 */

public class RewardPhase {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("type")
        private String type;
        @SerializedName("id")
        private String id;
        @SerializedName("attributes")
        private Attributes attributes;
        @SerializedName("links")
        private Links links;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public static class Attributes {
            @SerializedName("nama")
            private String nama;
            @SerializedName("status")
            private int status;
            @SerializedName("created-at")
            private Object createdat;
            @SerializedName("updated-at")
            private String updatedat;

            public String getNama() {
                return nama;
            }

            public void setNama(String nama) {
                this.nama = nama;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getCreatedat() {
                return createdat;
            }

            public void setCreatedat(Object createdat) {
                this.createdat = createdat;
            }

            public String getUpdatedat() {
                return updatedat;
            }

            public void setUpdatedat(String updatedat) {
                this.updatedat = updatedat;
            }
        }

        public static class Links {
            @SerializedName("self")
            private String self;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }
        }
    }
}
