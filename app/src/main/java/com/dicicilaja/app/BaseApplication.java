package com.dicicilaja.app;

import androidx.multidex.MultiDexApplication;


public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

//        sessionUser = new SessionManager(BaseApplication.this);
//
//        try {
//            JSONObject tags = new JSONObject();
//            tags.put("user_id", sessionUser.getUserId());
//            tags.put("profile_id", sessionUser.getUserId());
//            tags.put("role", sessionUser.getRole());
//            OneSignal.sendTags(tags);
//        } catch (Exception ex) {}
//
//        OneSignal.startInit(this)
////                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
////                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .init();

    }

//    private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
//        @Override
//        public void notificationReceived(OSNotification notification) {
//            JSONObject data = notification.payload.additionalData;
//            String notificationID = notification.payload.notificationID;
//            String title = notification.payload.title;
//            String body = notification.payload.body;
//            String smallIcon = notification.payload.smallIcon;
//            String largeIcon = notification.payload.largeIcon;
//            String bigPicture = notification.payload.bigPicture;
//            String smallIconAccentColor = notification.payload.smallIconAccentColor;
//            String sound = notification.payload.sound;
//            String ledColor = notification.payload.ledColor;
//            int lockScreenVisibility = notification.payload.lockScreenVisibility;
//            String groupKey = notification.payload.groupKey;
//            String groupMessage = notification.payload.groupMessage;
//            String fromProjectNumber = notification.payload.fromProjectNumber;
//            String rawPayload = notification.payload.rawPayload;
//
//            String customKey;
//
//            Log.i("OneSignalExample", "NotificationID received: " + notificationID);
//
//            if (data != null) {
//                customKey = data.optString("customkey", null);
//                if (customKey != null)
//                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
//            }
//        }
//    }
//
//
//    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
//        // This fires when a notification is opened by tapping on it.
//        @Override
//        public void notificationOpened(OSNotificationOpenResult result) {
//            OSNotificationAction.ActionType actionType = result.action.type;
//            JSONObject data = result.notification.payload.additionalData;
//            String launchUrl = result.notification.payload.launchURL; // update docs launchUrl
//
//            String customKey;
//            String openURL = null;
//            Object activityToLaunch = NotificationActivity.class;
//
//            if (data != null) {
//                customKey = data.optString("customkey", null);
//                openURL = data.optString("openURL", null);
//
//                if (customKey != null)
//                    Log.i("OneSignalExample", "customkey set with value: " + customKey);
//
//                if (openURL != null)
//                    Log.i("OneSignalExample", "openURL to webview with URL value: " + openURL);
//            }
//
//            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
//                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
//
//                if (result.action.actionID.equals("id1")) {
//                    Log.i("OneSignalExample", "button id called: " + result.action.actionID);
//                    activityToLaunch = AxiDashboardActivity.class;
//                } else
//                    Log.i("OneSignalExample", "button id called: " + result.action.actionID);
//            }
//            // The following can be used to open an Activity of your choice.
//            // Replace - getApplicationContext() - with any Android Context.
//            // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
//            Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//            // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("openURL", openURL);
//            Log.i("OneSignalExample", "openURL = " + openURL);
//            // startActivity(intent);
//            startActivity(intent);
//
//            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
//            //   if you are calling startActivity above.
//        /*
//           <application ...>
//             <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
//           </application>
//        */
//        }
//    }
}
