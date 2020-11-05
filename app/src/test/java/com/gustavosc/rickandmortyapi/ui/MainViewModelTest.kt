package com.gustavosc.rickandmortyapi.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.model.Location
import com.gustavosc.rickandmortyapi.data.model.Origin
import com.gustavosc.rickandmortyapi.data.repository.MainRepository
import com.gustavosc.rickandmortyapi.data.repository.dto.ListOfCharactersDto
import com.gustavosc.rickandmortyapi.ui.main.viewmodel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class MainViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    private val repository: MainRepository = mockk()
    private lateinit var viewModel: MainViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun before() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun shouldReturnOneListOfCharacter() = runBlocking {
        coEvery { repository.getAllCharacters() } returns Response.success(
            createObjectListOfCharacter()
        )
        viewModel.getAllCharacters()
        viewModel.character.observeForever {
            Assert.assertTrue(it.isNotEmpty())
        }
    }

    @Test
    fun shouldReturnOneListOfCharacterEmpty() = runBlocking {
        coEvery { repository.getAllCharacters() } returns Response.success(
            createObjectListOfCharacterEmpty()
        )
        viewModel.getAllCharacters()
        viewModel.character.observeForever {
            Assert.assertTrue(it.isEmpty())
        }
    }

    private fun createObjectListOfCharacterEmpty(): ListOfCharactersDto {
        return ListOfCharactersDto(arrayListOf())
    }

    private fun createObjectListOfCharacter(): ListOfCharactersDto {
        val character1 = Character(1, "", "", "", "", "", Origin("", ""), Location("", ""), "")
        val character2 = Character(2, "", "", "", "", "", Origin("", ""), Location("", ""), "")
        val character3 = Character(3, "", "", "", "", "", Origin("", ""), Location("", ""), "")

        return ListOfCharactersDto(arrayListOf(character1, character2, character3))
    }
}