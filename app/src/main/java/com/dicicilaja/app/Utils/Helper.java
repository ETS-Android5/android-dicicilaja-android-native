package com.dicicilaja.app.Utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Helper {
    public static String ConvertBitmapToString(Bitmap bitmap){
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        return encodedImage;
    }
}
