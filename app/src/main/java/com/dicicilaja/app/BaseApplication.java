package com.dicicilaja.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.dicicilaja.app.Activity.LoginActivity;
import com.dicicilaja.app.Activity.SplashActivity;
import com.dicicilaja.app.Inbox.UI.InboxActivity;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;


public class BaseApplication extends MultiDexApplication {


    String openInbox;
    @Override
    public void onCreate() {
        super.onCreate();
//  untuk check log onesignal
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);

//  implement onesignal
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

    }


    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
//            Log.d("Intent", "open inbox base: " + openInbox);
            JSONObject data = result.notification.payload.additionalData;

            if (data != null) {
                openInbox = data.optString("openInbox", null);
                if (openInbox != null) {
//                    Log.d("Intent", "open inbox base: " + openInbox);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("openInbox", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }


        }
    }
}
