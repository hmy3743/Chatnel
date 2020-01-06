package com.myhan.chatnel.di

import com.myhan.chatnel.model.Repository
import com.myhan.chatnel.model.RepositoryImpl
import com.myhan.chatnel.viewmodel.MainViewmodel
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewmodel(get()) }
}