package com.gustavosc.rickandmortyapi.data.api

import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.repository.dto.ListOfCharactersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    suspend fun getAllCharacter(): Response<ListOfCharactersDto>

    @GET("character")
    suspend fun getListCharcterFromPage(@Query("page")page: Int): Response<ListOfCharactersDto>

    @GET("character/{array}")
    suspend fun getRandomCharacter(@Path("array") array: ArrayList<Int>): Response<java.util.ArrayList<Character>>

}