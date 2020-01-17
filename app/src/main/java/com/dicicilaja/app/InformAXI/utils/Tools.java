package com.dicicilaja.app.InformAXI.utils;


import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Husni with ❤
 */

public class Tools {

    /* for Drawable Image */
    public static void displayImage(Context mContext, int drawable, ImageView imageView) {
        Glide.with(mContext)
                .load(drawable)
                .into(imageView);
    }

    /* Toast Utils */
    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /* Date Formatter */
    public static String formatDate(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = formatDate.format(date);
        return formattedDate;
    }

    public static String formatDateParams(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatDate.format(date);
        return formattedDate;
    }

    public static String formatDate(String date) {
        SimpleDateFormat sdfLokal = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("in", "ID"));
        SimpleDateFormat sdfDb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("in", "ID"));
        //sdfDb.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = "5 September 2019";
        try {
            Date tmpDate = sdfDb.parse(date);
            formattedDate = sdfLokal.format(tmpDate);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String formatDateWithoutHour(String date) {
        SimpleDateFormat sdfLokal = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("in", "ID"));
        SimpleDateFormat sdfDb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("in", "ID"));
        //sdfDb.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = "5 September 2019";
        try {
            Date tmpDate = sdfDb.parse(date);
            formattedDate = sdfLokal.format(tmpDate);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String formatMount(String date) {
        SimpleDateFormat sdfLokal = new SimpleDateFormat("MMMM yyyy", new Locale("in", "ID"));
        SimpleDateFormat sdfDb = new SimpleDateFormat("yyyy-MM-dd", new Locale("in", "ID"));
        //sdfDb.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = "September 2019";
        try {
            Date tmpDate = sdfDb.parse(date);
            formattedDate = sdfLokal.format(tmpDate);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

}
