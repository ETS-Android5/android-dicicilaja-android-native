package com.dicicilaja.app.API.Item.Product;

import java.util.ArrayList;

/**
 * Created by fawazrifqi on 15/04/18.
 */

public class SectionDataModel {
    private String headerTitle;
    private ArrayList<SingleItemModel> allItemInSection;

    public SectionDataModel() {
    }

    public SectionDataModel(String headerTitle, ArrayList<SingleItemModel> allItemInSection) {
        this.headerTitle = headerTitle;
        this.allItemInSection = allItemInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItemModel> getAllItemInSection() {
        return allItemInSection;
    }

    public void setAllItemInSection(ArrayList<SingleItemModel> allItemInSection) {
        this.allItemInSection = allItemInSection;
    }
}
