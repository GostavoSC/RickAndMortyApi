package com.gustavosc.rickandmortyapi.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.repository.MainRepository
import com.gustavosc.rickandmortyapi.ui.main.utils.getFiveRandomNumbers
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _character = MutableLiveData<ArrayList<Character>>()
    val character :LiveData<ArrayList<Character>> = _character
    val fiveCharacterRandom = MutableLiveData<ArrayList<Character>>()
    fun getAllCharacters() {
        viewModelScope.launch {
            mainRepository.also {
                if (it.getAllCharacters().isSuccessful) {
                    _character.postValue(it.getAllCharacters().body()!!.results)
                } else {
                    _character.postValue(arrayListOf())
                }
            }

        }
    }

    fun getOnlyFiveCharacters() {
        viewModelScope.launch {
            mainRepository.also{
                val numbers = getFiveRandomNumbers()
                if (it.getRandomCharacters(numbers).isSuccessful) {
                    fiveCharacterRandom.value.orEmpty()
                    fiveCharacterRandom.postValue(it.getRandomCharacters(numbers).body())
                } else {
                    fiveCharacterRandom.postValue(arrayListOf())
                }
            }
        }
    }

    fun getCharactersFromPage(page: Int) {
        viewModelScope.launch {
            mainRepository.also {
                if (it.getFromPage(page).isSuccessful) {
                    _character.value.orEmpty()
                    _character.postValue(it.getFromPage(page).body()!!.results)
                } else {
                    _character.postValue(arrayListOf())
                }
            }
        }
    }
}

