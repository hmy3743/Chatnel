package com.myhan.chatnel

import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.myhan.chatnel.viewmodel.MainViewmodel

@BindingAdapter("app:onTextChanged")
fun onTextChanged(view: TextInputEditText, vm: MainViewmodel) {
    view.doOnTextChanged { text, start, count, after ->
        if(vm.nickname != text.toString())
            vm.onNicknameChange(text.toString())
    }
}
