package com.myhan.chatnel.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.messaging.FirebaseMessaging
import com.myhan.chatnel.model.ChatMessage
import com.myhan.chatnel.model.Repository
import com.myhan.chatnel.randomString

class MainViewmodel(
    val repo: Repository,
    val nicknameLiveData: MutableLiveData<String>,
    val messageLiveData: MutableLiveData<String>,
    val chatLogLiveData: MutableLiveData<ChatMessage>
): ViewModel() {
    var nickname: String = "nickname"
        private set
    var message: String = ""

    val uid: String
        get() = repo.uid

    init {
        nicknameLiveData.postValue(nickname)
        messageLiveData.postValue(message)
        repo.getMessage().subscribe {
            Log.d("debug", "subscribe: $it")
            chatLogLiveData.postValue(it)
        }
        repo.connect().subscribe{
            Log.d("debug", "Websocket.Event: $it")
        }
    }

    fun getChatLogAt(index: Int) = repo.messageAt(index)

    fun onNicknameChange(value: String) {
        nickname = value
        nicknameLiveData.postValue(nickname)
    }

    fun onMessageChange(value: String) {
        message = value
        messageLiveData.postValue(message)
    }

    fun onMessageSend() {
        repo.sendMessage(ChatMessage(content = message, owner = nickname, type = "chat/plainText", uid = repo.uid))
        onMessageChange("")
    }

    fun onGetAllMessage() = repo.getMessages()
}