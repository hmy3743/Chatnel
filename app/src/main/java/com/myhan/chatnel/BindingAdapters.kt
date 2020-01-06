package com.myhan.chatnel

import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.myhan.chatnel.viewmodel.MainViewmodel

@BindingAdapter("app:onTextChanged")
fun onTextChanged(view: TextInputEditText, viewmodel: MainViewmodel) {
    view.doOnTextChanged { text, start, count, after ->
        Log.d("debug", "$text")
        viewmodel.nickname = text.toString()
    }
}
