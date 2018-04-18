package id.variable.dicicilaja.API.Item.Product;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class SingleItemModel {
    private String name, url, description;

    public SingleItemModel() {

    }

    public SingleItemModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
