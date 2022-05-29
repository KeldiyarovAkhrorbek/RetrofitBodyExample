package com.example.myapplication.repository

import com.example.myapplication.models.ReceiveData

sealed class PassportResource {
    object Loading : PassportResource()
    data class Success(val receiveData: ReceiveData?) : PassportResource()
    data class Error(val message: String) : PassportResource()
}