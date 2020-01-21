package com.myhan.chatnel.model.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myhan.chatnel.model.Entity.ChatMessageEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM CHAT_MESSAGE")
    fun getAll(): Single<List<ChatMessageEntity>>

    @Query("SELECT * FROM CHAT_MESSAGE WHERE id=:id")
    fun get(id: Int): Single<ChatMessageEntity?>

    @Query("SELECT * FROM CHAT_MESSAGE ORDER BY id DESC LIMIT 1")
    fun getLast(): Flowable<ChatMessageEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg entityChatMessage: ChatMessageEntity)
}