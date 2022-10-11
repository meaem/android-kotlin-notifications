package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "MyFBMessageService"

    override fun onMessageReceived(remoteMsg: RemoteMessage?) {
        Log.d(TAG, "onMessageReceived")

        remoteMsg?.data?.let {
            Log.d(TAG, "Data Received: ${remoteMsg.data}")
        }
        remoteMsg?.notification?.let {
            sendNotification(it.body!!)
        }

    }

    private fun sendNotification(body: String) {
        val notiMgr = ContextCompat.getSystemService(
            applicationContext, NotificationManager::class.java
        ) as NotificationManager
        notiMgr.sendNotification(body, applicationContext)
    }

    override fun onNewToken(token: String?) {

        Log.d(TAG, "Refreshed token: $token")


        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {

    }
}