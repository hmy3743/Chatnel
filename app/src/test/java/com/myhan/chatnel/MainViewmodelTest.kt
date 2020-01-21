package com.myhan.chatnel

import androidx.lifecycle.MutableLiveData
import com.myhan.chatnel.model.ChatMessage
import com.myhan.chatnel.model.Repository
import com.myhan.chatnel.util.LiveDataPostValueCalledException
import com.myhan.chatnel.viewmodel.MainViewmodel
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowExactly
import io.kotlintest.specs.StringSpec
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.reactivex.Single

class MainViewmodelTest : StringSpec({
    "onNicknameChange should change nickname" {
        val testNickame = "testNickname"

        val mockNicknameLiveData: MutableLiveData<String> = mockk()
        every { mockNicknameLiveData.postValue(any()) } just Runs

        val vm = MainViewmodel(mockk(), mockNicknameLiveData, mockk(relaxed = true), mockk(relaxed = true))

        vm.onNicknameChange(testNickame)

        vm.nickname shouldBe testNickame
    }
    "onNicknameChange should call nicknameLiveData.postValue" {
        val testNickame = "testNickname"
        val nicknameLiveData: MutableLiveData<String> = mockk()

        every { nicknameLiveData.postValue(any()) } just Runs
        every { nicknameLiveData.postValue(testNickame) } throws LiveDataPostValueCalledException()

        val vm = MainViewmodel(mockk(), nicknameLiveData, mockk(relaxed = true), mockk(relaxed = true))

        shouldThrowExactly<LiveDataPostValueCalledException> { vm.onNicknameChange(testNickame) }
    }
    "getChatLogAt should get message that index = id" {
        val repo: Repository = mockk()

        val index = 2
        val ret: Single<ChatMessage?> = mockk(name = "2")

        every { repo.messageAt(2) } returns ret

        val vm = MainViewmodel(repo, mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true))
        vm.getChatLogAt(index) shouldBe ret
    }
})