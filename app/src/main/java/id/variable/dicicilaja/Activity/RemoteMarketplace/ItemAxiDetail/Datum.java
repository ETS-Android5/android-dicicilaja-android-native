package id.variable.dicicilaja.Activity.RemoteMarketplace.ItemAxiDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 29/04/18.
 */

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("axi_id")
    @Expose
    private String axiId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("registered_date")
    @Expose
    private String registeredDate;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("axi_id_reff")
    @Expose
    private Object axiIdReff;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("ktp_number")
    @Expose
    private Object ktpNumber;
    @SerializedName("birth_place")
    @Expose
    private Object birthPlace;
    @SerializedName("birth_date")
    @Expose
    private Object birthDate;
    @SerializedName("debtor_name")
    @Expose
    private Object debtorName;
    @SerializedName("ktp_address")
    @Expose
    private Object ktpAddress;
    @SerializedName("ktp_rt_rw")
    @Expose
    private Object ktpRtRw;
    @SerializedName("subdistrict_ktp")
    @Expose
    private Object subdistrictKtp;
    @SerializedName("district_ktp")
    @Expose
    private Object districtKtp;
    @SerializedName("zipcode_ktp")
    @Expose
    private Object zipcodeKtp;
    @SerializedName("province_ktp")
    @Expose
    private Object provinceKtp;
    @SerializedName("correspond_address")
    @Expose
    private Object correspondAddress;
    @SerializedName("correspond_rt_rw")
    @Expose
    private Object correspondRtRw;
    @SerializedName("correspond_subdistrict")
    @Expose
    private Object correspondSubdistrict;
    @SerializedName("correspond_district")
    @Expose
    private Object correspondDistrict;
    @SerializedName("correspond_city")
    @Expose
    private Object correspondCity;
    @SerializedName("correspond_province")
    @Expose
    private Object correspondProvince;
    @SerializedName("correspond_zipcode")
    @Expose
    private Object correspondZipcode;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("bank_name")
    @Expose
    private Object bankName;
    @SerializedName("bank_account_name")
    @Expose
    private Object bankAccountName;
    @SerializedName("bank_branch")
    @Expose
    private Object bankBranch;
    @SerializedName("bank_city")
    @Expose
    private Object bankCity;
    @SerializedName("npwp_number")
    @Expose
    private Object npwpNumber;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("ktp_image")
    @Expose
    private Object ktpImage;
    @SerializedName("npwp_image")
    @Expose
    private Object npwpImage;
    @SerializedName("tabungan_image")
    @Expose
    private Object tabunganImage;
    @SerializedName("marital_status")
    @Expose
    private Object maritalStatus;
    @SerializedName("partner_name")
    @Expose
    private Object partnerName;
    @SerializedName("partner_ktp_number")
    @Expose
    private Object partnerKtpNumber;
    @SerializedName("npwp_name")
    @Expose
    private Object npwpName;
    @SerializedName("status_npwp")
    @Expose
    private Object statusNpwp;
    @SerializedName("pkp_status")
    @Expose
    private Object pkpStatus;
    @SerializedName("point_reward")
    @Expose
    private Integer pointReward;
    @SerializedName("point_trip")
    @Expose
    private Integer pointTrip;
    @SerializedName("incentive_car")
    @Expose
    private Integer incentiveCar;
    @SerializedName("incentive_car_mentor")
    @Expose
    private Integer incentiveCarMentor;
    @SerializedName("incentive_car_extra_bulanan")
    @Expose
    private Integer incentiveCarExtraBulanan;
    @SerializedName("incentive_car_group")
    @Expose
    private Integer incentiveCarGroup;
    @SerializedName("incentive_car_bonus_tahunan")
    @Expose
    private Integer incentiveCarBonusTahunan;
    @SerializedName("incentive_car_bonus_layout")
    @Expose
    private Integer incentiveCarBonusLayout;
    @SerializedName("incentive_mcy")
    @Expose
    private Integer incentiveMcy;
    @SerializedName("incentive_mcy_mentor")
    @Expose
    private Integer incentiveMcyMentor;
    @SerializedName("incentive_mcy_extra_bulanan")
    @Expose
    private Integer incentiveMcyExtraBulanan;
    @SerializedName("incentive_mcy_group")
    @Expose
    private Integer incentiveMcyGroup;
    @SerializedName("incentive_mcy_bonus_tahunan")
    @Expose
    private Integer incentiveMcyBonusTahunan;
    @SerializedName("incentive_mcy_bonus_layout")
    @Expose
    private Integer incentiveMcyBonusLayout;
    @SerializedName("rb_count")
    @Expose
    private Integer rbCount;
    @SerializedName("rb_level")
    @Expose
    private Integer rbLevel;
    @SerializedName("replica_web_link")
    @Expose
    private String replicaWebLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAxiId() {
        return axiId;
    }

    public void setAxiId(String axiId) {
        this.axiId = axiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getAxiIdReff() {
        return axiIdReff;
    }

    public void setAxiIdReff(Object axiIdReff) {
        this.axiIdReff = axiIdReff;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getKtpNumber() {
        return ktpNumber;
    }

    public void setKtpNumber(Object ktpNumber) {
        this.ktpNumber = ktpNumber;
    }

    public Object getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Object birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public Object getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(Object debtorName) {
        this.debtorName = debtorName;
    }

    public Object getKtpAddress() {
        return ktpAddress;
    }

    public void setKtpAddress(Object ktpAddress) {
        this.ktpAddress = ktpAddress;
    }

    public Object getKtpRtRw() {
        return ktpRtRw;
    }

    public void setKtpRtRw(Object ktpRtRw) {
        this.ktpRtRw = ktpRtRw;
    }

    public Object getSubdistrictKtp() {
        return subdistrictKtp;
    }

    public void setSubdistrictKtp(Object subdistrictKtp) {
        this.subdistrictKtp = subdistrictKtp;
    }

    public Object getDistrictKtp() {
        return districtKtp;
    }

    public void setDistrictKtp(Object districtKtp) {
        this.districtKtp = districtKtp;
    }

    public Object getZipcodeKtp() {
        return zipcodeKtp;
    }

    public void setZipcodeKtp(Object zipcodeKtp) {
        this.zipcodeKtp = zipcodeKtp;
    }

    public Object getProvinceKtp() {
        return provinceKtp;
    }

    public void setProvinceKtp(Object provinceKtp) {
        this.provinceKtp = provinceKtp;
    }

    public Object getCorrespondAddress() {
        return correspondAddress;
    }

    public void setCorrespondAddress(Object correspondAddress) {
        this.correspondAddress = correspondAddress;
    }

    public Object getCorrespondRtRw() {
        return correspondRtRw;
    }

    public void setCorrespondRtRw(Object correspondRtRw) {
        this.correspondRtRw = correspondRtRw;
    }

    public Object getCorrespondSubdistrict() {
        return correspondSubdistrict;
    }

    public void setCorrespondSubdistrict(Object correspondSubdistrict) {
        this.correspondSubdistrict = correspondSubdistrict;
    }

    public Object getCorrespondDistrict() {
        return correspondDistrict;
    }

    public void setCorrespondDistrict(Object correspondDistrict) {
        this.correspondDistrict = correspondDistrict;
    }

    public Object getCorrespondCity() {
        return correspondCity;
    }

    public void setCorrespondCity(Object correspondCity) {
        this.correspondCity = correspondCity;
    }

    public Object getCorrespondProvince() {
        return correspondProvince;
    }

    public void setCorrespondProvince(Object correspondProvince) {
        this.correspondProvince = correspondProvince;
    }

    public Object getCorrespondZipcode() {
        return correspondZipcode;
    }

    public void setCorrespondZipcode(Object correspondZipcode) {
        this.correspondZipcode = correspondZipcode;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public Object getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(Object bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public Object getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(Object bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Object getBankCity() {
        return bankCity;
    }

    public void setBankCity(Object bankCity) {
        this.bankCity = bankCity;
    }

    public Object getNpwpNumber() {
        return npwpNumber;
    }

    public void setNpwpNumber(Object npwpNumber) {
        this.npwpNumber = npwpNumber;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public Object getKtpImage() {
        return ktpImage;
    }

    public void setKtpImage(Object ktpImage) {
        this.ktpImage = ktpImage;
    }

    public Object getNpwpImage() {
        return npwpImage;
    }

    public void setNpwpImage(Object npwpImage) {
        this.npwpImage = npwpImage;
    }

    public Object getTabunganImage() {
        return tabunganImage;
    }

    public void setTabunganImage(Object tabunganImage) {
        this.tabunganImage = tabunganImage;
    }

    public Object getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Object maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Object getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(Object partnerName) {
        this.partnerName = partnerName;
    }

    public Object getPartnerKtpNumber() {
        return partnerKtpNumber;
    }

    public void setPartnerKtpNumber(Object partnerKtpNumber) {
        this.partnerKtpNumber = partnerKtpNumber;
    }

    public Object getNpwpName() {
        return npwpName;
    }

    public void setNpwpName(Object npwpName) {
        this.npwpName = npwpName;
    }

    public Object getStatusNpwp() {
        return statusNpwp;
    }

    public void setStatusNpwp(Object statusNpwp) {
        this.statusNpwp = statusNpwp;
    }

    public Object getPkpStatus() {
        return pkpStatus;
    }

    public void setPkpStatus(Object pkpStatus) {
        this.pkpStatus = pkpStatus;
    }

    public Integer getPointReward() {
        return pointReward;
    }

    public void setPointReward(Integer pointReward) {
        this.pointReward = pointReward;
    }

    public Integer getPointTrip() {
        return pointTrip;
    }

    public void setPointTrip(Integer pointTrip) {
        this.pointTrip = pointTrip;
    }

    public Integer getIncentiveCar() {
        return incentiveCar;
    }

    public void setIncentiveCar(Integer incentiveCar) {
        this.incentiveCar = incentiveCar;
    }

    public Integer getIncentiveCarMentor() {
        return incentiveCarMentor;
    }

    public void setIncentiveCarMentor(Integer incentiveCarMentor) {
        this.incentiveCarMentor = incentiveCarMentor;
    }

    public Integer getIncentiveCarExtraBulanan() {
        return incentiveCarExtraBulanan;
    }

    public void setIncentiveCarExtraBulanan(Integer incentiveCarExtraBulanan) {
        this.incentiveCarExtraBulanan = incentiveCarExtraBulanan;
    }

    public Integer getIncentiveCarGroup() {
        return incentiveCarGroup;
    }

    public void setIncentiveCarGroup(Integer incentiveCarGroup) {
        this.incentiveCarGroup = incentiveCarGroup;
    }

    public Integer getIncentiveCarBonusTahunan() {
        return incentiveCarBonusTahunan;
    }

    public void setIncentiveCarBonusTahunan(Integer incentiveCarBonusTahunan) {
        this.incentiveCarBonusTahunan = incentiveCarBonusTahunan;
    }

    public Integer getIncentiveCarBonusLayout() {
        return incentiveCarBonusLayout;
    }

    public void setIncentiveCarBonusLayout(Integer incentiveCarBonusLayout) {
        this.incentiveCarBonusLayout = incentiveCarBonusLayout;
    }

    public Integer getIncentiveMcy() {
        return incentiveMcy;
    }

    public void setIncentiveMcy(Integer incentiveMcy) {
        this.incentiveMcy = incentiveMcy;
    }

    public Integer getIncentiveMcyMentor() {
        return incentiveMcyMentor;
    }

    public void setIncentiveMcyMentor(Integer incentiveMcyMentor) {
        this.incentiveMcyMentor = incentiveMcyMentor;
    }

    public Integer getIncentiveMcyExtraBulanan() {
        return incentiveMcyExtraBulanan;
    }

    public void setIncentiveMcyExtraBulanan(Integer incentiveMcyExtraBulanan) {
        this.incentiveMcyExtraBulanan = incentiveMcyExtraBulanan;
    }

    public Integer getIncentiveMcyGroup() {
        return incentiveMcyGroup;
    }

    public void setIncentiveMcyGroup(Integer incentiveMcyGroup) {
        this.incentiveMcyGroup = incentiveMcyGroup;
    }

    public Integer getIncentiveMcyBonusTahunan() {
        return incentiveMcyBonusTahunan;
    }

    public void setIncentiveMcyBonusTahunan(Integer incentiveMcyBonusTahunan) {
        this.incentiveMcyBonusTahunan = incentiveMcyBonusTahunan;
    }

    public Integer getIncentiveMcyBonusLayout() {
        return incentiveMcyBonusLayout;
    }

    public void setIncentiveMcyBonusLayout(Integer incentiveMcyBonusLayout) {
        this.incentiveMcyBonusLayout = incentiveMcyBonusLayout;
    }

    public Integer getRbCount() {
        return rbCount;
    }

    public void setRbCount(Integer rbCount) {
        this.rbCount = rbCount;
    }

    public Integer getRbLevel() {
        return rbLevel;
    }

    public void setRbLevel(Integer rbLevel) {
        this.rbLevel = rbLevel;
    }

    public String getReplicaWebLink() {
        return replicaWebLink;
    }

    public void setReplicaWebLink(String replicaWebLink) {
        this.replicaWebLink = replicaWebLink;
    }
}
