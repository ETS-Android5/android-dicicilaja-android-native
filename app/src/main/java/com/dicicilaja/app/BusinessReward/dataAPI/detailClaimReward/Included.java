package com.dicicilaja.app.BusinessReward.dataAPI.detailClaimReward;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husni with ❤
 */

public class Included {
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private int id;
    @SerializedName("attributes")
    private AttributesX attributes;
    @SerializedName("relationships")
    private RelationshipsX relationships;

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

    public AttributesX getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesX attributes) {
        this.attributes = attributes;
    }

    public RelationshipsX getRelationships() {
        return relationships;
    }

    public void setRelationships(RelationshipsX relationships) {
        this.relationships = relationships;
    }

    public static class AttributesX {
        @SerializedName("vendor_id")
        private int vendorId;
        @SerializedName("point")
        private int point = -1;
        @SerializedName("nama")
        private String nama;
        @SerializedName("deskripsi")
        private String deskripsi;
        @SerializedName("harga")
        private int harga;
        @SerializedName("foto")
        private String foto;
        @SerializedName("alt_foto")
        private String altFoto;
        @SerializedName("popularitas")
        private int popularitas;

        public int getVendorId() {
            return vendorId;
        }

        public void setVendorId(int vendorId) {
            this.vendorId = vendorId;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public int getHarga() {
            return harga;
        }

        public void setHarga(int harga) {
            this.harga = harga;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public String getAltFoto() {
            return altFoto;
        }

        public void setAltFoto(String altFoto) {
            this.altFoto = altFoto;
        }

        public int getPopularitas() {
            return popularitas;
        }

        public void setPopularitas(int popularitas) {
            this.popularitas = popularitas;
        }
    }

    public static class RelationshipsX {
        @SerializedName("status")
        private StatusX status;
        @SerializedName("kategori")
        private Kategori kategori;

        public StatusX getStatus() {
            return status;
        }

        public void setStatus(StatusX status) {
            this.status = status;
        }

        public Kategori getKategori() {
            return kategori;
        }

        public void setKategori(Kategori kategori) {
            this.kategori = kategori;
        }

        public static class StatusX {
            @SerializedName("data")
            private DataXXX data;

            public DataXXX getData() {
                return data;
            }

            public void setData(DataXXX data) {
                this.data = data;
            }

            public static class DataXXX {
                @SerializedName("type")
                private String type;
                @SerializedName("id")
                private int id;

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
            }
        }

        public static class Kategori {
            @SerializedName("data")
            private DataXXXX data;

            public DataXXXX getData() {
                return data;
            }

            public void setData(DataXXXX data) {
                this.data = data;
            }

            public static class DataXXXX {
                @SerializedName("type")
                private String type;
                @SerializedName("id")
                private int id;

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
            }
        }
    }
}
