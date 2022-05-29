package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.SendData
import com.example.myapplication.repository.PassportRepository
import com.example.myapplication.repository.PassportResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val passportRepository: PassportRepository) :
    ViewModel() {

    fun sendDataGet(sendData: SendData): StateFlow<PassportResource> {
        val stateFlow = MutableStateFlow<PassportResource>(PassportResource.Loading)
        viewModelScope.launch {
            val flow = passportRepository.getReceived(sendData)
            flow.catch {
                stateFlow.emit(PassportResource.Error(it.message ?: ""))
            }.collect {
                if (it.isSuccessful) {
                    stateFlow.emit(PassportResource.Success(it.body()))
                } else {
                    stateFlow.emit(PassportResource.Error(it.message() ?: ""))
                }
            }
        }
        return stateFlow
    }

}