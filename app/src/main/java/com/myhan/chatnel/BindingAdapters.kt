package com.myhan.chatnel

import android.util.Log
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.myhan.chatnel.model.ChatMessage
import com.myhan.chatnel.viewmodel.MainViewmodel
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@BindingAdapter("onNicknameChanged")
fun onTextChanged(view: TextInputEditText, vm: MainViewmodel) {
    view.doOnTextChanged { text, start, count, after ->
        if(vm.nickname != text.toString())
            vm.onNicknameChange(text.toString())
    }
}

@BindingAdapter("onMessageChanged")
fun onTextChanged(view: EditText, vm: MainViewmodel) {
    view.doOnTextChanged { text, start, count, after ->
        if(vm.message != text.toString())
            vm.onMessageChange(text.toString())
    }
}

@BindingAdapter("item")
fun bindItem(view: RecyclerView, chatLogLiveData: LiveData<ChatMessage>) {
    val adapter = view.adapter as ChatLogRecyclerviewItemAdapter
    if(chatLogLiveData.value != null && adapter.logList.lastOrNull()?.guid != chatLogLiveData.value!!.guid) {
        Log.d("debug", "${adapter.logList.lastOrNull()?.guid} != ${chatLogLiveData.value!!.guid}")
        adapter.logList.add(chatLogLiveData.value!!)
        adapter.notifyItemInserted(adapter.logList.count() - 1)
        view.scrollToPosition(adapter.logList.count() - 1)
    }
}

@BindingAdapter("initial-item")
fun bindItems(view: RecyclerView, chatLogs: Single<List<ChatMessage>>) {
    val adapter = view.adapter as ChatLogRecyclerviewItemAdapter
    chatLogs
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { it, err ->
            Log.d("debug", "err: $err")
            adapter.logList.clear()
            adapter.logList.addAll(it)
            adapter.notifyDataSetChanged()
            view.scrollToPosition(adapter.logList.count() - 1)
        }
}
