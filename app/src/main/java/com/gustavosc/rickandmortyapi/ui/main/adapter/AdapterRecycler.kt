package com.gustavosc.rickandmortyapi.ui.main.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gustavosc.rickandmortyapi.R
import com.gustavosc.rickandmortyapi.data.model.Character
import java.util.*

class AdapterRecycler(
    private val characters: ArrayList<Character>,
    private val context: Context
) :
    RecyclerView.Adapter<AdapterRecycler.DataViewHolder>() {

    companion object {
        lateinit var onClick: BtnClickListener
    }

    private fun searchImage(url: String, holder: DataViewHolder) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .override(400, 400)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    setBackgroundColor(resource, holder)
                    holder.image.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    //TODO
                }
            })
    }

    fun addListCharacter(list: ArrayList<Character>) {
        characters.clear()
        characters.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_recycler, parent, false)
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val character = characters[position]
        holder.name.text = character.name
        holder.state.text = character.status
        holder.breed.text = character.species
        searchImage(character.image, holder)
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

    fun setBackgroundColor(bitmap: Bitmap, holder: DataViewHolder) {
        val vibrantSwatch = createPaletteSync(bitmap).vibrantSwatch
        val bb = createPaletteSync(bitmap).dominantSwatch
        with(holder.layout) {
            setBackgroundColor(
                vibrantSwatch?.rgb ?: bb?.rgb ?: ContextCompat.getColor(context, R.color.white)
            )
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ConstraintLayout = itemView.findViewById(R.id.layoutRecycler)
        val name: TextView = itemView.findViewById(R.id.nameCharacterTextView)
        val state: TextView = itemView.findViewById(R.id.stateLiveTextView)
        val breed: TextView = itemView.findViewById(R.id.breedOfCharacterTextView)
        val image: ImageView = itemView.findViewById(R.id.imageOfCharacterImageView)
    }

    private fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

}