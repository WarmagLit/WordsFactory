package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.repository.model.Definition

class DefinitionAdapter : RecyclerView.Adapter<DefinitionHolder>() {

    var definitions: List<Definition> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_definition, parent, false)
        return DefinitionHolder(view)
    }

    override fun onBindViewHolder(holder: DefinitionHolder, position: Int) {
        val character = definitions[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = definitions.size
}

class DefinitionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textDefinition: TextView = itemView.findViewById(R.id.textDefinition)
    private val textExample: TextView = itemView.findViewById(R.id.textExample)
    //private val recyclerPartMeaning: RecyclerView = itemView.findViewById(R.id.recyclerPartMeaning)


    fun bind(definition: Definition) {
        textDefinition.text = definition.definition
        textExample.text = definition.example
    }

}