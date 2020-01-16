package com.myhan.chatnel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhan.chatnel.databinding.ActivityMainBinding
import com.myhan.chatnel.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewmodel: MainViewmodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewmodel
        binding.lifecycleOwner = this

        recyclerViewChatLog.adapter = ChatLogRecyclerviewItemAdapter(this, mutableListOf(), viewmodel.uid)
        recyclerViewChatLog.layoutManager = LinearLayoutManager(this).apply { stackFromEnd = true }
    }
}
