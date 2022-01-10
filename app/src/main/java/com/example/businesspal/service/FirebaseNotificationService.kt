package com.example.businesspal.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseNotificationService : FirebaseMessagingService() {
    val TAG = javaClass.name
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }
}