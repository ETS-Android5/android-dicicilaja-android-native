package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemDetailProgramMaxi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fawazrifqi on 10/05/18.
 */

public class Data {
    @SerializedName("id_program")
    @Expose
    private String idProgram;
    @SerializedName("title_program")
    @Expose
    private String titleProgram;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("jenis_program")
    @Expose
    private String jenisProgram;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("partner")
    @Expose
    private String partner;
    @SerializedName("tenor")
    @Expose
    private List<Tenor> tenor = null;

    public String getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(String idProgram) {
        this.idProgram = idProgram;
    }

    public String getTitleProgram() {
        return titleProgram;
    }

    public void setTitleProgram(String titleProgram) {
        this.titleProgram = titleProgram;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getJenisProgram() {
        return jenisProgram;
    }

    public void setJenisProgram(String jenisProgram) {
        this.jenisProgram = jenisProgram;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public List<Tenor> getTenor() {
        return tenor;
    }

    public void setTenor(List<Tenor> tenor) {
        this.tenor = tenor;
    }
}
