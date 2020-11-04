package com.gustavosc.rickandmortyapi.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gustavosc.rickandmortyapi.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun getAllCharacters() = liveData(Dispatchers.IO){
        mainRepository.also {
            if (it.getAllCharacters().isSuccessful){
                emit(it.getAllCharacters().body()!!.results)
            }
        }

    }

}