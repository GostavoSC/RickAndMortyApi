package com.gustavosc.rickandmortyapi.data.api

import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.repository.dto.ListOfCharactersDto

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllCharacters() = apiService.getAllCharacter()



}