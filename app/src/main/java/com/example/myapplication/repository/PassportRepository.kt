package com.example.myapplication.repository


import com.example.myapplication.di.networking.ApiService
import com.example.myapplication.models.SendData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PassportRepository @Inject constructor(private val apiService: ApiService) {

    fun getReceived(sendData: SendData) =
        flow { emit(apiService.postData(sendData)) }

}