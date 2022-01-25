package com.gustavosc.rickandmortyapi.ui.main.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gustavosc.rickandmortyapi.R
import com.gustavosc.rickandmortyapi.data.api.ApiHelper
import com.gustavosc.rickandmortyapi.data.api.RetrofitBuilder
import com.gustavosc.rickandmortyapi.data.repository.MainRepository
import com.gustavosc.rickandmortyapi.ui.main.adapter.AdapterRecycler
import com.gustavosc.rickandmortyapi.ui.main.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class HomeActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: AdapterRecycler
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        startKoin {
            androidContext(this@HomeActivity)
            modules(viewModelModule)
        }
        setupObservers()
        setupAdapter()
    }

    private fun setupObservers() {
        viewModel.getOnlyFiveCharacters()
        viewModel.fiveCharacterRandom.observe(this) {
            viewAdapter.addListCharacter(it)
        }
    }

    private fun setupAdapter() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = AdapterRecycler(arrayListOf(),this)
        recyclerView = findViewById<RecyclerView>(R.id.homeRecycler).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private val viewModelModule = module {
        factory { MainViewModel(get()) }
        single { MainRepository(get()) }
        single { ApiHelper(get()) }
        single { RetrofitBuilder.apiService }
    }

}