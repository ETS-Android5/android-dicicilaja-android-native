package id.variable.dicicilaja.Activity.RemoteMarketplace.Item.ItemMaxiTravel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fawazrifqi on 13/05/18.
 */

public class Data {
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("product")
    @Expose
    private List<Product> product = null;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
