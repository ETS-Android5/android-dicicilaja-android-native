package com.dicicilaja.app.InformAXI.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Husni with ❤
 */

public class AxiProfile {

    @SerializedName("data")
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("profile")
        private ProfileBean profile;
        @SerializedName("benefit")
        private BenefitBean benefit;
        @SerializedName("lainnya")
        private LainnyaBean lainnya;

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public BenefitBean getBenefit() {
            return benefit;
        }

        public void setBenefit(BenefitBean benefit) {
            this.benefit = benefit;
        }

        public LainnyaBean getLainnya() {
            return lainnya;
        }

        public void setLainnya(LainnyaBean lainnya) {
            this.lainnya = lainnya;
        }

        public static class ProfileBean {
            @SerializedName("namaAXI")
            private String namaAXI;
            @SerializedName("idAXI")
            private int idAXI;
            @SerializedName("kategoriAXI")
            private String kategoriAXI;
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

        public static class BenefitBean {
            @SerializedName("Point_reward")
            private int pointReward = -1;
            @SerializedName("Point_trip")
            private int pointTrip = -1;
            @SerializedName("FIDCar")
            private String FIDCar;
            @SerializedName("FIDMcy")
            private String FIDMcy;
            @SerializedName("matriksAXI")
            private String matriksAXI;
            @SerializedName("linkIntensifMotor")
            private String linkIntensifMotor;
            @SerializedName("linkIntensifMobil")
            private String linkIntensifMobil;

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

            public String getLinkIntensifMotor() {
                return linkIntensifMotor;
            }

            public void setLinkIntensifMotor(String linkIntensifMotor) {
                this.linkIntensifMotor = linkIntensifMotor;
            }

            public String getLinkIntensifMobil() {
                return linkIntensifMobil;
            }

            public void setLinkIntensifMobil(String linkIntensifMobil) {
                this.linkIntensifMobil = linkIntensifMobil;
            }
        }

        public static class LainnyaBean {
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
