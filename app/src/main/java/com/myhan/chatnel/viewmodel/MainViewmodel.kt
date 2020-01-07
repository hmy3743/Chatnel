package com.myhan.chatnel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myhan.chatnel.model.Repository

class MainViewmodel(val repo: Repository): ViewModel() {
    fun sayHello() = "${repo.getHello()} from $this"

    var nickname: String = "nickname"
    val nicknameLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        nicknameLiveData.postValue(nickname)
    }

    fun onNicknameChange(value: String) {
        nickname = value
        nicknameLiveData.postValue(nickname)
    }
}