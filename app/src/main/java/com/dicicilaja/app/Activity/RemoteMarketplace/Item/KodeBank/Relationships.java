
package com.dicicilaja.app.Activity.RemoteMarketplace.Item.KodeBank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationships {

    @SerializedName("accounts")
    @Expose
    private Accounts accounts;

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

}
