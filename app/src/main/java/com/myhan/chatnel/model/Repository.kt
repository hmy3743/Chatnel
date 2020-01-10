package com.myhan.chatnel.model

import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable

interface Repository {
    fun getMessage(): Flowable<ChatMessage>

    fun connect(): Flowable<WebSocket.Event>

    fun sendMessage(message: ChatMessage)

    fun messageAt(index: Int): ChatMessage?
}