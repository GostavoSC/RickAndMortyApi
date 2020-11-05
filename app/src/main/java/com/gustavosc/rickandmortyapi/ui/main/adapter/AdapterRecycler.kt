package com.gustavosc.rickandmortyapi.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gustavosc.rickandmortyapi.R
import com.gustavosc.rickandmortyapi.data.model.Character
import java.util.*

class AdapterRecycler(
    private val characters: ArrayList<Character>,
    val btnlistener: BtnClickListener,
    val context: Context
) :
    RecyclerView.Adapter<AdapterRecycler.DataViewHolder>() {

    companion object {
        lateinit var onClick: BtnClickListener
    }

    private fun searchImage(url: String, holder: DataViewHolder) {
        Glide.with(context)
            .load(url)
            .override(600, 700)
            .into(holder.image)
    }

    fun addListCharacter(list: ArrayList<Character>) {
        characters.clear()
        characters.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_recycler, parent, false)
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val character = characters[position]
        onClick = btnlistener
        holder.name.text = character.name
        holder.state.text = character.status
        holder.breed.text = character.species
        holder.gender.text = character.gender
        searchImage(character.image, holder)
        holder.button.setOnClickListener {
            onClick.onBtnClick(character, holder)
        }
    }

    fun verifyIfTextButtonIsHide(name: String): Boolean {
        var condition = false
        if (name == "hide") {
            condition = true
        }
        return condition
    }

    interface BtnClickListener {
        fun onBtnClick(character: Character, holder: DataViewHolder)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.nameCharacterTextView)
        val state = itemView.findViewById<TextView>(R.id.stateLiveTextView)
        val breed = itemView.findViewById<TextView>(R.id.breedOfCharacterTextView)
        val gender = itemView.findViewById<TextView>(R.id.genderCharacterTextView)
        val image = itemView.findViewById<ImageView>(R.id.imageOfCharacterImageView)
        val button = itemView.findViewById<Button>(R.id.showDetailsButton)
    }


}