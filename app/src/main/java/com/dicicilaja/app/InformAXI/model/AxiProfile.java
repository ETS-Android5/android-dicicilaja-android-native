package com.dicicilaja.app.InformAXI.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Husni with ❤
 */

public class AxiProfile {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("profile")
        private Profile profile;
        @SerializedName("benefit")
        private Benefit benefit;
        @SerializedName("lainnya")
        private Lainnya lainnya;

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        public Benefit getBenefit() {
            return benefit;
        }

        public void setBenefit(Benefit benefit) {
            this.benefit = benefit;
        }

        public Lainnya getLainnya() {
            return lainnya;
        }

        public void setLainnya(Lainnya lainnya) {
            this.lainnya = lainnya;
        }

        public static class Profile {
            @SerializedName("namaAXI")
            private String namaAXI;
            @SerializedName("idAXI")
            private int idAXI;
            @SerializedName("kategoriAXI")
            private String kategoriAXI;
            @SerializedName("AXImentor")
            private String AXImentor;
            @SerializedName("nomorHandphone")
            private String nomorHandphone;
            @SerializedName("namaCabang")
            private String namaCabang;
            @SerializedName("kodeCabang")
            private String kodeCabang;
            @SerializedName("Area")
            private String Area;

            public String getNamaAXI() {
                return namaAXI;
            }

            public void setNamaAXI(String namaAXI) {
                this.namaAXI = namaAXI;
            }

            public int getIdAXI() {
                return idAXI;
            }

            public void setIdAXI(int idAXI) {
                this.idAXI = idAXI;
            }

            public String getKategoriAXI() {
                return kategoriAXI;
            }

            public void setKategoriAXI(String kategoriAXI) {
                this.kategoriAXI = kategoriAXI;
            }

            public String getAXImentor() {
                return AXImentor;
            }

            public void setAXImentor(String AXImentor) {
                this.AXImentor = AXImentor;
            }

            public String getNomorHandphone() {
                return nomorHandphone;
            }

            public void setNomorHandphone(String nomorHandphone) {
                this.nomorHandphone = nomorHandphone;
            }

            public String getNamaCabang() {
                return namaCabang;
            }

            public void setNamaCabang(String namaCabang) {
                this.namaCabang = namaCabang;
            }

            public String getKodeCabang() {
                return kodeCabang;
            }

            public void setKodeCabang(String kodeCabang) {
                this.kodeCabang = kodeCabang;
            }

            public String getArea() {
                return Area;
            }

            public void setArea(String Area) {
                this.Area = Area;
            }
        }

        public static class Benefit {
            @SerializedName("Point_reward")
            private int PointReward;
            @SerializedName("Point_trip")
            private int PointTrip;
            @SerializedName("FIDCar")
            private String FIDCar;
            @SerializedName("FIDMcy")
            private String FIDMcy;
            @SerializedName("matriksAXI")
            private String matriksAXI;

            public int getPointReward() {
                return PointReward;
            }

            public void setPointReward(int PointReward) {
                this.PointReward = PointReward;
            }

            public int getPointTrip() {
                return PointTrip;
            }

            public void setPointTrip(int PointTrip) {
                this.PointTrip = PointTrip;
            }

            public String getFIDCar() {
                return FIDCar;
            }

            public void setFIDCar(String FIDCar) {
                this.FIDCar = FIDCar;
            }

            public String getFIDMcy() {
                return FIDMcy;
            }

            public void setFIDMcy(String FIDMcy) {
                this.FIDMcy = FIDMcy;
            }

            public String getMatriksAXI() {
                return matriksAXI;
            }

            public void setMatriksAXI(String matriksAXI) {
                this.matriksAXI = matriksAXI;
            }
        }

        public static class Lainnya {
            @SerializedName("tanggalPendaftaran")
            private String tanggalPendaftaran;
            @SerializedName("jatuhTempoKeanggotaan")
            private String jatuhTempoKeanggotaan;
            @SerializedName("status")
            private String status;

            public String getTanggalPendaftaran() {
                return tanggalPendaftaran;
            }

            public void setTanggalPendaftaran(String tanggalPendaftaran) {
                this.tanggalPendaftaran = tanggalPendaftaran;
            }

            public String getJatuhTempoKeanggotaan() {
                return jatuhTempoKeanggotaan;
            }

            public void setJatuhTempoKeanggotaan(String jatuhTempoKeanggotaan) {
                this.jatuhTempoKeanggotaan = jatuhTempoKeanggotaan;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
