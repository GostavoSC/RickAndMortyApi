package com.gustavosc.rickandmortyapi.data.api

import com.gustavosc.rickandmortyapi.data.repository.dto.ListOfCharactersDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character/")
    suspend fun getAllCharacter(): Response<ListOfCharactersDto>
}