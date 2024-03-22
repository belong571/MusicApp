package com.example.musicapp.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.bean.Response
import com.example.musicapp.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var response = MutableLiveData<Response>()

    fun getSong(input: String, type: Int){
        viewModelScope.launch {
            flow {
                emit(NetworkService.api.getSongList(input,"200", "HK"))
            }.flowOn(Dispatchers.IO).catch { e ->
                e.printStackTrace()
            }.collect {
                if(type == 1){
                    val result = it.results
                    val newResult = result.sortedBy { item -> item.trackPrice}
                    for (item in newResult){
                        Log.d("xxxxxxxxx"," price ${item.trackPrice}")
                    }
                    val a=0
                    it.results = newResult
                }
                response.value = it
            }
        }
    }
}