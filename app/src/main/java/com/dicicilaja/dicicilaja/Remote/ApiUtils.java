package com.dicicilaja.dicicilaja.Remote;

import com.dicicilaja.dicicilaja.API.Interface.InterfaceDraft;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceKeputusanPinjaman;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceKeputusanSurvey;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceLogout;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceNilaiPinjaman;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceNotifToken;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceRequestSurvey;
import com.dicicilaja.dicicilaja.API.Interface.InterfaceSurveyFinish;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceAxiDetail;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.InterfaceCreateRequest;
import com.dicicilaja.dicicilaja.Activity.RemoteMarketplace.InterfaceAxi.LoginMarketplace;

/**
 * Created by ziterz on 11/29/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://sandbox.dicicilaja.com/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static InterfaceCreateRequest getCreateRequest() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceCreateRequest.class);
    }

    public static InterfaceAxiDetail getAxiDetail() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceAxiDetail.class);
    }

    public static LoginMarketplace getLogin() {
        return RetrofitClient.getClient(BASE_URL).create(LoginMarketplace.class);
    }

    public static UserFirebase getUserFirebase() {
        return RetrofitClient.getClient(BASE_URL).create(UserFirebase.class);
    }

    public static AreaService getAreaService() {
        return RetrofitClient.getClient(BASE_URL).create(AreaService.class);
    }

    public static RequestProcess getRequestService() {
        return RetrofitClient.getClient(BASE_URL).create(RequestProcess.class);
    }

    public static InterfaceRequestSurvey getRequestSurvey() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceRequestSurvey.class);
    }

    public static InterfaceDraft getDraft() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceDraft.class);
    }

    public static InterfaceSurveyFinish getSurvey() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceSurveyFinish.class);
    }

    public static InterfaceKeputusanSurvey getKeputusanSurvey() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceKeputusanSurvey.class);
    }

    public static InterfaceNilaiPinjaman getNilaiPinjaman() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceNilaiPinjaman.class);
    }

    public static InterfaceKeputusanPinjaman getKeputusanPinjaman() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceKeputusanPinjaman.class);
    }

    public static InterfaceNotifToken getNotifToken() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceNotifToken.class);
    }

    public static InterfaceLogout getLogout() {
        return RetrofitClient.getClient(BASE_URL).create(InterfaceLogout.class);
    }


}
