package com.myhan.chatnel.model

import android.annotation.SuppressLint
import android.util.Log
import com.myhan.chatnel.MessageOuterClass
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable

@SuppressLint("CheckResult")
class RepositoryImpl(
    private val chatService: ChatService,
    private val messages: MutableList<ChatMessage>
): Repository {
    override fun getMessage(): Flowable<ChatMessage> {
        return chatService.observeMessage().map {
            Log.d("debug", "${it.owner}, ${it.content}, ${it.type}")
            ChatMessage(owner = it.owner, content = it.content, type = it.type)
        }
    }

    override fun connect(): Flowable<WebSocket.Event> = chatService.observeWebSocketEvent()

    override fun sendMessage(message: ChatMessage) {
        val protoMessage = MessageOuterClass.Message.newBuilder()
        protoMessage.owner = message.owner
        protoMessage.content = message.content
        protoMessage.type = message.type

        chatService.sendChatMessage(protoMessage.build())
    }

    override fun messageAt(index: Int): ChatMessage? = messages.elementAtOrNull(index)
}