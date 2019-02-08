package com.dicicilaja.app.API.Item;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("current_page")
    private int mCurrentPage;

    @SerializedName("from")
    private int mFrom;

    @SerializedName("last_page")
    private int mLastPage;

    @SerializedName("path")
    private String mPath;

    @SerializedName("per_page")
    private int mPerPage;

    @SerializedName("to")
    private int mTo;

    @SerializedName("total")
    private int mTotal;

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int mCurrentPage) {
        this.mCurrentPage = mCurrentPage;
    }

    public int getFrom() {
        return mFrom;
    }

    public void setFrom(int mFrom) {
        this.mFrom = mFrom;
    }

    public int getLastPage() {
        return mLastPage;
    }

    public void setLastPage(int mLastPage) {
        this.mLastPage = mLastPage;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    public int getPerPage() {
        return mPerPage;
    }

    public void setPerPage(int mPerPage) {
        this.mPerPage = mPerPage;
    }

    public int getTo() {
        return mTo;
    }

    public void setTo(int mTo) {
        this.mTo = mTo;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int mTotal) {
        this.mTotal = mTotal;
    }
}
