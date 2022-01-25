package com.gustavosc.rickandmortyapi.ui.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ScrollView
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

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: AdapterRecycler
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val viewModel: MainViewModel by inject()
    private lateinit var nextButton: Button
    private lateinit var previewButton: Button
    private var page: Int = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            androidContext(this@MainActivity)
            modules(viewModelModule)
        }
        setupAdapter()
        setupObserverListOfCharacter()
        setupButtons()
    }

    private fun setupButtons() {
        nextButton = findViewById(R.id.nextButton)
        previewButton = findViewById(R.id.previewButton)

        nextButton.setOnClickListener {
            page++
            Log.e("value: ", page.toString())
            viewModel.getCharactersFromPage(page)

        }

        previewButton.setOnClickListener {
            if (page > 1) {
                previewButton.isClickable = true
                page--
                viewModel.getCharactersFromPage(page)

            } else {
                previewButton.isClickable = false
            }

        }
    }


    private val viewModelModule = module {
        factory { MainViewModel(get()) }
        single { MainRepository(get()) }
        single { ApiHelper(get()) }
        single { RetrofitBuilder.apiService }
    }

    private fun setupAdapter() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = AdapterRecycler(arrayListOf(), this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setupObserverListOfCharacter() {
        viewModel.getAllCharacters()
        viewModel.character.observe(this) {
            if (it.isNotEmpty()) {
                viewAdapter.addListCharacter(it)
            }
        }
    }

}