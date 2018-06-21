//ApiClient.java
package com.dicicilaja.app.Upload;


import com.dicicilaja.app.Upload.model.UserProfile;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created on 10/15/17.
 */

public interface ApiClient {

    @Multipart
    @POST("/coba_upload")
    Call<UserProfile> uploadImage(@Part("client") String fileName, @Part MultipartBody.Part file);

}
