package com.example.myapplication.di.networking

import com.example.myapplication.models.ReceiveData
import com.example.myapplication.models.SendData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/GetAvto/rest/service_MIP2/getMipPass")
    suspend fun postData(@Body sendData: SendData) : Response<ReceiveData>

}