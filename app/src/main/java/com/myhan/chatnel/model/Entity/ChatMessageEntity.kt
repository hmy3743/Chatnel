package com.myhan.chatnel.model.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CHAT_MESSAGE")
data class ChatMessageEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val owner: String,
    val content: String,
    val type: String,
    val uid: String
)