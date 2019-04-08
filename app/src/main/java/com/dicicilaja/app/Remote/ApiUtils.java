package com.dicicilaja.app.Remote;

import com.dicicilaja.app.API.Interface.InterfaceCreateRequest;
import com.dicicilaja.app.API.Interface.InterfaceDraft;
import com.dicicilaja.app.API.Interface.InterfaceKeputusanPinjaman;
import com.dicicilaja.app.API.Interface.InterfaceKeputusanSurvey;
import com.dicicilaja.app.API.Interface.InterfaceLogout;
import com.dicicilaja.app.API.Interface.InterfaceNilaiPinjaman;
import com.dicicilaja.app.API.Interface.InterfaceNotifToken;
import com.dicicilaja.app.API.Interface.InterfaceRequestSurvey;
import com.dicicilaja.app.API.Interface.InterfaceSurveyFinish;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.app.Activity.RemoteMarketplace.InterfaceAxi.LoginMarketplace;

/**
 * Created by ziterz on 11/29/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://sandbox.dicicilaja.com/";
    public static final String BASE_URL2 = "http://api.dicicilaja.com/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL2).create(UserService.class);
    }

    public static InterfaceCreateRequest getCreateRequest() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceCreateRequest.class);
    }

    public static InterfaceAxiDetail getAxiDetail() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceAxiDetail.class);
    }

    public static ClaimProcess getClaim() {
        return RetrofitClient.getClient(BASE_URL2).create(ClaimProcess.class);
    }


    public static LoginMarketplace getLogin() {
        return RetrofitClient.getClient(BASE_URL2).create(LoginMarketplace.class);
    }

    public static UserFirebase getUserFirebase() {
        return RetrofitClient.getClient(BASE_URL2).create(UserFirebase.class);
    }

    public static AreaService getAreaService() {
        return RetrofitClient.getClient(BASE_URL2).create(AreaService.class);
    }

    public static RequestProcess getRequestService() {
        return RetrofitClient.getClient(BASE_URL2).create(RequestProcess.class);
    }

    public static InterfaceRequestSurvey getRequestSurvey() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceRequestSurvey.class);
    }

    public static InterfaceDraft getDraft() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceDraft.class);
    }

    public static InterfaceSurveyFinish getSurvey() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceSurveyFinish.class);
    }

    public static InterfaceKeputusanSurvey getKeputusanSurvey() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceKeputusanSurvey.class);
    }

    public static InterfaceNilaiPinjaman getNilaiPinjaman() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceNilaiPinjaman.class);
    }

    public static InterfaceKeputusanPinjaman getKeputusanPinjaman() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceKeputusanPinjaman.class);
    }

    public static InterfaceNotifToken getNotifToken() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceNotifToken.class);
    }

    public static InterfaceLogout getLogout() {
        return RetrofitClient.getClient(BASE_URL2).create(InterfaceLogout.class);
    }


}