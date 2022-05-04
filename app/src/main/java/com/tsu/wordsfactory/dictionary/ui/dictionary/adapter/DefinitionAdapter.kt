package com.tsu.wordsfactory.dictionary.ui.dictionary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.ItemDefinitionBinding
import com.tsu.wordsfactory.repository.model.Definition

class DefinitionAdapter : RecyclerView.Adapter<DefinitionHolder>() {

    var definitions: List<Definition> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionHolder {
        val itemBinding = ItemDefinitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DefinitionHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DefinitionHolder, position: Int) {
        val character = definitions[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = definitions.size
}

class DefinitionHolder(private val itemBinding: ItemDefinitionBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(definition: Definition) {
        itemBinding.textDefinition.text = definition.definition
        itemBinding.textExample.text = definition.example
    }

}