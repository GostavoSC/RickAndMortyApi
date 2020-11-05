package com.gustavosc.rickandmortyapi.ui.main.adapter

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AdapterRecyclerTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var adapter: AdapterRecycler

    private val listener: AdapterRecycler.BtnClickListener = mockk()
    private val context: Context = mockk()

    @Before
    fun before() {
        adapter = AdapterRecycler(arrayListOf(), listener, context)
    }

    @Test
    fun shouldReturnTrueIfTextButtonIsHide() {
        Assert.assertTrue(adapter.verifyIfTextButtonIsHide("hide"))
    }
    @Test
    fun shouldReturnFalseIfTextButtonNoIsHide() {
        Assert.assertFalse(adapter.verifyIfTextButtonIsHide("unhide"))
    }


}