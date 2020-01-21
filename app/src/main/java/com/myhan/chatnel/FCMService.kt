package com.myhan.chatnel

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.myhan.chatnel.model.Dao.ChatMessageDao
import com.myhan.chatnel.model.Entity.ChatMessageEntity
import org.koin.android.ext.android.inject

class FCMService: FirebaseMessagingService() {
    private val chatMessageDao: ChatMessageDao by inject()

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("debug", "FCMService.onMessageReceived(${message.data})")
        chatMessageDao.insert(ChatMessageEntity(
            owner = message.data["owner"]!!,
            content = message.data["content"]!!,
            type = message.data["type"]!!,
            uid = message.data["uid"]!!,
            guid = message.data["guid"]!!
        ))
    }
}