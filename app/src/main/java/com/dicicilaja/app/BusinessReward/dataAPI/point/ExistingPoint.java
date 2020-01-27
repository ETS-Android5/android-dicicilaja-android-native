package com.dicicilaja.app.BusinessReward.dataAPI.point;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husni with ❤
 */

public class ExistingPoint {

    @SerializedName("links")
    private Links links;
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("included")
    private List<?> included;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<?> getIncluded() {
        return included;
    }

    public void setIncluded(List<?> included) {
        this.included = included;
    }

    public static class Links {
        @SerializedName("first")
        private String first;
        @SerializedName("last")
        private String last;
        @SerializedName("prev")
        private Object prev;
        @SerializedName("next")
        private Object next;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public Object getPrev() {
            return prev;
        }

        public void setPrev(Object prev) {
            this.prev = prev;
        }

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
            this.next = next;
        }
    }

    public static class Meta {
        @SerializedName("current_page")
        private int currentPage;
        @SerializedName("from")
        private int from;
        @SerializedName("last_page")
        private int lastPage;
        @SerializedName("path")
        private String path;
        @SerializedName("per_page")
        private int perPage;
        @SerializedName("to")
        private int to;
        @SerializedName("total")
        private int total;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class Data {
        @SerializedName("type")
        private String type;
        @SerializedName("id")
        private int id;
        @SerializedName("attributes")
        private Attributes attributes;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public static class Attributes {
            @SerializedName("profile_id")
            private String profileId;
            @SerializedName("point_reward")
            private int pointReward;
            @SerializedName("point_trip")
            private int pointTrip;
            @SerializedName("insentif_mcy")
            private int insentifMcy;
            @SerializedName("insentif_car")
            private int insentifCar;

            public String getProfileId() {
                return profileId;
            }

            public void setProfileId(String profileId) {
                this.profileId = profileId;
            }

            public int getPointReward() {
                return pointReward;
            }

            public void setPointReward(int pointReward) {
                this.pointReward = pointReward;
            }

            public int getPointTrip() {
                return pointTrip;
            }

            public void setPointTrip(int pointTrip) {
                this.pointTrip = pointTrip;
            }

            public int getInsentifMcy() {
                return insentifMcy;
            }

            public void setInsentifMcy(int insentifMcy) {
                this.insentifMcy = insentifMcy;
            }

            public int getInsentifCar() {
                return insentifCar;
            }

            public void setInsentifCar(int insentifCar) {
                this.insentifCar = insentifCar;
            }
        }
    }
}
