package com.gustavosc.rickandmortyapi.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllFirstCharacters() = apiService.getAllCharacter()
    suspend fun getRandomCharacter(array: ArrayList<Int>) = apiService.getRandomCharacter(array)
    suspend fun getFromPage(page: Int) = apiService.getListCharcterFromPage(page)



}