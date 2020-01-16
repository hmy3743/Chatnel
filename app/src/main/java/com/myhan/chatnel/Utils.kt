package com.myhan.chatnel

import android.content.Context

private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun randomString(length: Int) = (1 .. charPool.count()).map { kotlin.random.Random.nextInt(0, charPool.count()) }.map(
    charPool::get).joinToString("")

class Registry(private val context: Context) {
    val uid: String
        get() {
            val sharedPref = context.getSharedPreferences("default", Context.MODE_PRIVATE)
            val uid = sharedPref.getString("uid", null)
            when(uid) {
                null -> {
                    sharedPref.edit().putString("uid", randomString(128)).commit()
                    return sharedPref.getString("uid", null)!!
                }
                else -> return uid
            }
        }
}