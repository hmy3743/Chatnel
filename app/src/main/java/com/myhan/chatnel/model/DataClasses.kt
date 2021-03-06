package com.myhan.chatnel.model

data class ChatMessage(
    val owner: String,
    val content: String,
    val type: String,
    val uid: String? = null,
    val guid: String? = null
)