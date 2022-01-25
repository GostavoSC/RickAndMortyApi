package com.gustavosc.rickandmortyapi.ui.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.gustavosc.rickandmortyapi.R

open class ItemRecyclerCustom @JvmOverloads
constructor(
    private val ctx: Context,
    private val fragment: Fragment? = null,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ConstraintLayout(ctx, attributeSet, defStyleAttr) {

    private var nameCharacter: TextView = findViewById(R.id.nameCharacterTextView)
    private var name = ""
    var stateCharacter: String = ""
    var breedCharacter: String = ""


    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_item_recycler, this)

        initViews()
    }

    private fun initViews() {
        nameCharacter.text = name
    }

    open fun agentDescription(): String? {
        return ""
    }

    open fun name(name: String): ItemRecyclerCustom {
        this.name = name
        return this
    }

    open fun valueText(): String? {
        return ""
    }

    open fun id(): String {
        return ""
    }

    open fun type(): String {
        return ""
    }


    open fun submitValue(): Any? {
        return null
    }

}
