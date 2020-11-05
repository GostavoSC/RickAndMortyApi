package com.gustavosc.rickandmortyapi.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.repository.MainRepository
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    val character = MutableLiveData<ArrayList<Character>>()

    fun getAllCharacters() {
        viewModelScope.launch {
            mainRepository.also {
                if (it.getAllCharacters().isSuccessful) {
                    character.postValue(it.getAllCharacters().body()!!.results)
                } else {
                    character.postValue(arrayListOf())
                }
            }

        }
    }

}

