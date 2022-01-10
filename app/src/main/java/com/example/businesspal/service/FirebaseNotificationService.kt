package com.example.businesspal.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseNotificationService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
    }
}