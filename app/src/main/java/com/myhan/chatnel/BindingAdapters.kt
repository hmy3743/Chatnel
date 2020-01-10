package com.myhan.chatnel

import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.myhan.chatnel.model.ChatMessage
import com.myhan.chatnel.viewmodel.MainViewmodel

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
    Log.d("debug", "bindItem: ${chatLogLiveData.value}")
    if(chatLogLiveData.value != null) {
        adapter.logList.add(chatLogLiveData.value!!)
        adapter.notifyDataSetChanged()
    }
}