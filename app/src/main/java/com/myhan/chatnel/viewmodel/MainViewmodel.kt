package com.myhan.chatnel.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.myhan.chatnel.model.Repository

class MainViewmodel(val repo: Repository): ViewModel() {
    fun sayHello() = "${repo.getHello()} from $this"

    var nickname: String = "nickname"

}