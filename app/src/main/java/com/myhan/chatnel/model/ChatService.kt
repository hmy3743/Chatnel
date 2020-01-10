package com.myhan.chatnel.model

import com.myhan.chatnel.MessageOuterClass
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface ChatService {
    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>

    @Receive
    fun observeMessage(): Flowable<MessageOuterClass.Message>

    @Send
    fun sendChatMessage(message: MessageOuterClass.Message)
}