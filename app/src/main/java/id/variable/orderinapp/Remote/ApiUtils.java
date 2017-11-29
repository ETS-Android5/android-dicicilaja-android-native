package id.variable.orderinapp.Remote;

/**
 * Created by ziterz on 11/29/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://dicicilaja.com/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
