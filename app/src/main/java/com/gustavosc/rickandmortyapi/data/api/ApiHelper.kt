package com.gustavosc.rickandmortyapi.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllFirstCharacters() = apiService.getAllCharacter()

    suspend fun getFromPage(page: Int) = apiService.getListCharcterFromPage(page)



}