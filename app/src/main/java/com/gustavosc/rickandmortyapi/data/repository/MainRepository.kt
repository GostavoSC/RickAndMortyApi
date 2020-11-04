package com.gustavosc.rickandmortyapi.data.repository

import com.gustavosc.rickandmortyapi.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllCharacters() = apiHelper.getAllCharacters()
}