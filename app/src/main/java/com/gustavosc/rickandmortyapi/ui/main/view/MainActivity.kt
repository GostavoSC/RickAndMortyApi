package com.gustavosc.rickandmortyapi.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gustavosc.rickandmortyapi.R
import com.gustavosc.rickandmortyapi.data.api.ApiHelper
import com.gustavosc.rickandmortyapi.data.api.RetrofitBuilder
import com.gustavosc.rickandmortyapi.data.model.Character
import com.gustavosc.rickandmortyapi.data.repository.MainRepository
import com.gustavosc.rickandmortyapi.ui.main.adapter.AdapterRecycler
import com.gustavosc.rickandmortyapi.ui.main.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: AdapterRecycler
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            androidContext(this@MainActivity)
            modules(viewModelModule)
        }

        setupAdapter()
        setupObseverListOfCharacter()
        setupButtons()
        setupScroll()
    }

    private fun setupScroll() {
        scrollView = findViewById(R.id.scroll)

    }

    private fun setupButtons() {
        nextButton = findViewById(R.id.nextButton)
        previewButton = findViewById(R.id.previewButton)

        nextButton.setOnClickListener {
            page++
            Log.e("value: ",page.toString())
            viewModel.getCharactersFromPage(page)

        }

        previewButton.setOnClickListener {
            if (page > 1) {
                previewButton.isClickable = true
                page--
                viewModel.getCharactersFromPage(page)

            }else{
                previewButton.isClickable = false
            }

        }
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var nextButton: Button
    private lateinit var previewButton: Button
    private var page: Int = 1


    val viewModelModule = module {
        single { MainViewModel(get()) }
        single { MainRepository(get()) }
        single { ApiHelper(get()) }
        single { RetrofitBuilder.apiService }
    }

    private fun setupAdapter() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = AdapterRecycler(arrayListOf(), object : AdapterRecycler.BtnClickListener {
            override fun onBtnClick(character: Character, holder: AdapterRecycler.DataViewHolder) {
                if (viewAdapter.verifyIfTextButtonIsHide(holder.button.text.toString())) {
                    turnGoneVibility(holder)
                } else {
                    turnVisibleVibility(holder)
                }
            }
        }, this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun turnGoneVibility(holder: AdapterRecycler.DataViewHolder) {
        holder.image.visibility = View.GONE
        holder.button.text = getString(R.string.unhide_button_text)
    }

    private fun turnVisibleVibility(holder: AdapterRecycler.DataViewHolder) {
        holder.image.visibility = View.VISIBLE
        holder.button.text = getString(R.string.hide_button_text)
    }

    private fun setupObseverListOfCharacter() {
        viewModel.getAllCharacters()
        viewModel.character.observe(this) {
            if (it.isNotEmpty()) {
                viewAdapter.addListCharacter(it)
            }
        }
    }

}