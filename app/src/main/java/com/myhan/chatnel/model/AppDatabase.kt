package com.myhan.chatnel.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myhan.chatnel.model.Dao.ChatMessageDao
import com.myhan.chatnel.model.Entity.ChatMessageEntity

@Database(entities = arrayOf(
    ChatMessageEntity::class
), version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
}