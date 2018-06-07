package com.dicicilaja.dicicilaja.Upload.services;

import android.content.Context;
import android.util.Log;


import com.dicicilaja.dicicilaja.Activity.AjukanPengajuanAxi2Activity;
import com.dicicilaja.dicicilaja.Upload.ApiClient;
import com.dicicilaja.dicicilaja.Upload.ApiClientBuilder;
import com.dicicilaja.dicicilaja.Upload.model.UserProfile;
import com.dicicilaja.dicicilaja.Upload.utils.FileUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


public class Service {

    public void saveUserImage(Context context, String photoName, File imageFile, Callback<UserProfile> callback) {

        // create upload service client
        ApiClient service = ApiClientBuilder.getMGClient();

        RequestBody requestFile =
                RequestBody.create(MediaType.parse(FileUtils.getFileExtension(imageFile.getAbsolutePath())), imageFile);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("ktp", imageFile.getName(), requestFile);

        // add another part within the multipart request
        String imageName = photoName;
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, imageName);

        Call<UserProfile> result =  service.uploadImage(imageName, body);

        result.enqueue(callback);

    }

}
