package com.myhan.chatnel.model

import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {
    val uid: String

    fun getMessage(): Flowable<ChatMessage>

    fun getMessages(): Single<List<ChatMessage>>

    fun connect(): Flowable<WebSocket.Event>

    fun sendMessage(message: ChatMessage)

    fun messageAt(index: Int): Single<ChatMessage?>
}