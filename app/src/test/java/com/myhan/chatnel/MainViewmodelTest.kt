package com.myhan.chatnel

import androidx.lifecycle.MutableLiveData
import com.myhan.chatnel.model.ChatMessage
import com.myhan.chatnel.model.Repository
import com.myhan.chatnel.model.RepositoryImpl
import com.myhan.chatnel.viewmodel.MainViewmodel
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class MainViewmodelTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun runOnceBeforeTestStart() {
            println("@Before All")
        }

        @AfterAll
        @JvmStatic
        fun runOnceAfterTestsFinished() {
            println("@After All")
        }
    }

    private var mainViewmodel: MainViewmodel? = null
    private var repository: RepositoryImpl? = null
    private var nicknameLiveData: MutableLiveData<String>? = null
    private var messageLiveData: MutableLiveData<String>? = null
    private var chatLogLiveData: MutableLiveData<ChatMessage>? = null

    @BeforeEach
    internal fun runOnceBeforeEveryTest(info: TestInfo) {
        println("@Before Test ${info.displayName}")
    }

    @BeforeEach
    internal fun runOnceAfterEveryTest(info: TestInfo) {
        println("@After Test ${info.displayName}")
    }

    @Test
    @Disabled
    internal fun failingDisabledTest() {
        assertEquals(5, 2 + 2)
    }

    @Test
    internal fun ordinaryTestCase() {
        assertEquals(4, 2 + 2)
    }
}