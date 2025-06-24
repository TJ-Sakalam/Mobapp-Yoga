package com.example.yogaapp;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class GettingDeviceTokenService extends FirebaseMessagingService {

    private static final String TAG = "FCM Token";

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle FCM messages here if you want to process messages in this service
        // Otherwise, they'll be handled by MyFirebaseMessagingService
    }
}