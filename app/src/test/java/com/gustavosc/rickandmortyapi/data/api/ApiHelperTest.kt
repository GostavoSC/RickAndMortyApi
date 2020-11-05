package com.gustavosc.rickandmortyapi.data.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavosc.rickandmortyapi.data.repository.dto.ListOfCharactersDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class ApiHelperTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var apiHelper: ApiHelper
    private val apiService: ApiService = mockk()

    @Before
    fun before() {
        apiHelper = ApiHelper(apiService)
    }

    @Test
    fun shouldPassOneTurnInMethod() {
        coEvery { apiService.getAllCharacter() } returns Response.success(
            ListOfCharactersDto(
                arrayListOf()
            )
        )

        runBlocking {
            apiHelper.getAllCharacters()
        }

        coVerify(exactly = 1) { apiHelper.getAllCharacters() }
    }

}