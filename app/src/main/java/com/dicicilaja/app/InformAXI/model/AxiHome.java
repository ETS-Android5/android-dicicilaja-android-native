package com.dicicilaja.app.InformAXI.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AxiHome {
    @SerializedName("links")
    private LinksBean links;
    @SerializedName("meta")
    private MetaBean meta;
    @SerializedName("data")
    private List<DataBean> data;

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class LinksBean {
        @SerializedName("first")
        private String first;
        @SerializedName("last")
        private String last;
        @SerializedName("prev")
        private String prev;
        @SerializedName("next")
        private String next;

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

        public String getPrev() {
            return prev;
        }

        public void setPrev(String prev) {
            this.prev = prev;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }

    public static class MetaBean {
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

    public static class DataBean {
        @SerializedName("id")
        private int id;
        @SerializedName("nomor_axi_id")
        private String nomorAxiId;
        @SerializedName("nama")
        private String nama;
        @SerializedName("tanggal_daftar")
        private String tanggalDaftar;
        @SerializedName("link")
        private String link;
        @SerializedName("status_keanggotaan")
        private String statusKeanggotaan;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNomorAxiId() {
            return nomorAxiId;
        }

        public void setNomorAxiId(String nomorAxiId) {
            this.nomorAxiId = nomorAxiId;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getTanggalDaftar() {
            return tanggalDaftar;
        }

        public void setTanggalDaftar(String tanggalDaftar) {
            this.tanggalDaftar = tanggalDaftar;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getStatusKeanggotaan() {
            return statusKeanggotaan;
        }

        public void setStatusKeanggotaan(String statusKeanggotaan) {
            this.statusKeanggotaan = statusKeanggotaan;
        }
    }
}
