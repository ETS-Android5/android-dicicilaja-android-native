package com.dicicilaja.app.Content;

import java.util.ArrayList;

/**
 * Created by ziterz on 2/16/2018.
 */

public class SectionDataModel {
    private String headerTitle;
    private ArrayList<PromoModel> allItemInSection;

    public SectionDataModel() {
    }

    public SectionDataModel(String headerTitle, ArrayList<PromoModel> allItemInSection) {
        this.headerTitle = headerTitle;
        this.allItemInSection = allItemInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<PromoModel> getAllItemInSection() {
        return allItemInSection;
    }

    public void setAllItemInSection(ArrayList<PromoModel> allItemInSection) {
        this.allItemInSection = allItemInSection;
    }
}
