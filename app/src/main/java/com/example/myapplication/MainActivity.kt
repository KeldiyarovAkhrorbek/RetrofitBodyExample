package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.SendData
import com.example.myapplication.repository.PassportResource
import com.example.myapplication.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sendData = SendData("1994-08-24", "AB3165546")
        binding.button.setOnClickListener {
            sendAndGetData(sendData)
        }
    }

    private fun sendAndGetData(sendData: SendData) {
        lifecycleScope.launch {
            viewModel.sendDataGet(sendData).collect {
                when (it) {
                    is PassportResource.Loading -> {

                    }

                    is PassportResource.Success -> {
                        binding.text.text = it.receiveData.toString()
                    }

                    is PassportResource.Error -> {
                        binding.text.text = it.message
                    }
                }
            }
        }

    }
}