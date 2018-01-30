package id.variable.dicicilaja.Remote;

import id.variable.dicicilaja.API.Interface.InterfaceDraft;
import id.variable.dicicilaja.API.Interface.InterfaceRequestSurvey;

/**
 * Created by ziterz on 11/29/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.dicicilaja.com/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
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

}
