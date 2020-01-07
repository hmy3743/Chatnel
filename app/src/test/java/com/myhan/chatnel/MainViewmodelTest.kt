package com.myhan.chatnel

import androidx.lifecycle.Observer
import com.myhan.chatnel.model.RepositoryImpl
import com.myhan.chatnel.viewmodel.MainViewmodel
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MainViewmodelTest : StringSpec({
    "nickname should change after onNicknameChange" {
        val vm = MainViewmodel(RepositoryImpl())

        val testNickame = "testNickname"
        vm.onNicknameChange(testNickame)

        vm.nickname shouldBe testNickame
    }

})