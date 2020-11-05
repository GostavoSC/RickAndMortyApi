package com.gustavosc.rickandmortyapi.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavosc.rickandmortyapi.data.api.ApiHelper
import com.gustavosc.rickandmortyapi.data.repository.dto.ListOfCharactersDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class MainRepositoryTest() {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: MainRepository
    private val apiHelper: ApiHelper = mockk()

    @Before
    fun before() {
        repository = MainRepository(apiHelper)
    }

    @Test
    fun shouldPassInMethodOneTurn() {
        coEvery { apiHelper.getAllCharacters() } returns Response.success(
            ListOfCharactersDto(
                arrayListOf()
            )
        )
        runBlocking {
            repository.getAllCharacters()
        }
        coVerify(exactly = 1) { repository.getAllCharacters() }
    }

}