package id.variable.dicicilaja.API.Item.Recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fawazrifqi on 13/04/18.
 */

public class Merchant {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("handphone")
    @Expose
    private String handphone;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("ktp_number")
    @Expose
    private String ktpNumber;
    @SerializedName("personal_npwp_number")
    @Expose
    private String personalNpwpNumber;
    @SerializedName("company_npwp_number")
    @Expose
    private String companyNpwpNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("company_address")
    @Expose
    private String companyAddress;
    @SerializedName("program_id")
    @Expose
    private Integer programId;
    @SerializedName("image_ktp")
    @Expose
    private Object imageKtp;
    @SerializedName("image_npwp")
    @Expose
    private Object imageNpwp;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getKtpNumber() {
        return ktpNumber;
    }

    public void setKtpNumber(String ktpNumber) {
        this.ktpNumber = ktpNumber;
    }

    public String getPersonalNpwpNumber() {
        return personalNpwpNumber;
    }

    public void setPersonalNpwpNumber(String personalNpwpNumber) {
        this.personalNpwpNumber = personalNpwpNumber;
    }

    public String getCompanyNpwpNumber() {
        return companyNpwpNumber;
    }

    public void setCompanyNpwpNumber(String companyNpwpNumber) {
        this.companyNpwpNumber = companyNpwpNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Object getImageKtp() {
        return imageKtp;
    }

    public void setImageKtp(Object imageKtp) {
        this.imageKtp = imageKtp;
    }

    public Object getImageNpwp() {
        return imageNpwp;
    }

    public void setImageNpwp(Object imageNpwp) {
        this.imageNpwp = imageNpwp;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }
}
