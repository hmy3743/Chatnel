package com.myhan.chatnel.model

import com.myhan.chatnel.MessageOuterClass
import com.myhan.chatnel.Registry
import com.myhan.chatnel.model.Dao.ChatMessageDao
import com.myhan.chatnel.model.Entity.ChatMessageEntity
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.Single

class RepositoryImpl(
    private val registry: Registry,
    private val chatService: ChatService,
    private val messages: MutableList<ChatMessage>,
    private val chatMessageDao: ChatMessageDao
): Repository {
    init {
        chatService.observeMessage().subscribe {
            ChatMessageEntity(owner = it.owner, content = it.content, type = it.type, uid = it.uid, guid = it.guid).save()
        }
    }

    override val uid: String
        get() = registry.uid

    override fun getMessage(): Flowable<ChatMessage> = chatMessageDao.getLast().map { ChatMessage(owner = it.owner, content = it.content, type = it.type, uid = it.uid) }

    override fun getMessages(): Single<List<ChatMessage>> = chatMessageDao.getAll().map {
        it.map { ChatMessage(owner = it.owner, content = it.content, type = it.type) }
    }

    override fun connect(): Flowable<WebSocket.Event> = chatService.observeWebSocketEvent()

    override fun sendMessage(message: ChatMessage) {
        val protoMessage = MessageOuterClass.Message.newBuilder()
        protoMessage.owner = message.owner
        protoMessage.content = message.content
        protoMessage.type = message.type
        protoMessage.uid = message.uid

        chatService.sendChatMessage(protoMessage.build())
    }

    override fun messageAt(index: Int) = chatMessageDao.get(index).map{ ChatMessage(owner = it.owner, content = it.content, type = it.type) }

    private fun ChatMessageEntity.save() {
        chatMessageDao.insert(this)
    }
}