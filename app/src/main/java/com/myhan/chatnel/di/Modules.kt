package com.myhan.chatnel.di

import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import com.google.protobuf.Internal
import com.google.protobuf.MessageLite
import com.myhan.chatnel.Registry
import com.myhan.chatnel.model.*
import com.myhan.chatnel.viewmodel.MainViewmodel
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.protobuf.ProtobufMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

val appModule = module {
    factory { mutableListOf<ChatMessage>() }
    factory(named("LiveChatMessage")) { MutableLiveData<ChatMessage>() }
    factory(named("LiveString")) { MutableLiveData<String>() }

    single { Registry(androidContext()) }

    single {
        OkHttpClient.Builder().build()
    }

    single {
        Scarlet.Builder()
            .webSocketFactory(get<OkHttpClient>().newWebSocketFactory("ws://192.168.2.3:5000"))
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .addMessageAdapterFactory(ProtobufMessageAdapter.Factory())
            .build()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "database-local"
        ).build()
    }

    single { get<AppDatabase>().chatMessageDao() }

    single { get<Scarlet>().create<ChatService>() }

    single<Repository> { RepositoryImpl(get(), get(), get()) }

    viewModel { MainViewmodel(get(), get(named("LiveString")), get(named("LiveString")), get(named("LiveChatMessage"))) }
}