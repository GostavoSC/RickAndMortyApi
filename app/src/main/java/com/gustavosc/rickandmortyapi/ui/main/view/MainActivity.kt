package com.gustavosc.rickandmortyapi.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.gustavosc.rickandmortyapi.R
import com.gustavosc.rickandmortyapi.data.api.ApiHelper
import com.gustavosc.rickandmortyapi.data.api.RetrofitBuilder
import com.gustavosc.rickandmortyapi.ui.base.MainViewModelFactory
import com.gustavosc.rickandmortyapi.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObseverListOfCharacter()
    }

    private fun setupObseverListOfCharacter() {
        viewModel.getAllCharacters().observe(this){
            it
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(ApiHelper((RetrofitBuilder.apiService)))
        ).get(MainViewModel::class.java)
    }
}