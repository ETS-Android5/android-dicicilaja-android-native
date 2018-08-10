package com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi;

import com.dicicilaja.app.Activity.RemoteMarketplace.Item.ItemProfileAxi.ProfileAxi;

import retrofit2.Call;
import retrofit2.http.Header;

public interface InterfaceProfileBase {
    Call<ProfileAxi> getProfile(@Header("Authorization") String apiKey);
}
