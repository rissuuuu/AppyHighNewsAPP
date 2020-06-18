package com.appyhigh.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    Context mycontext;

    ExampleNotificationOpenedHandler(Context context) {
        mycontext= context;
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        Log.i("OSNotificationPayload", "result.notification.payload.toJSONObject().toString(): " + result.notification.payload.toJSONObject().toString());
        // Capture Launch URL (App URL) here
        JSONObject data = result.notification.payload.additionalData;
//        Log.i("appURL", appURL);
        String customKey;

        if (data != null) {
            customKey = data.optString("url", null);
            Intent intent=new Intent(mycontext,WebV.class);
            intent.putExtra("webview",customKey);
            mycontext.startActivity(intent);
        }

    }
}
