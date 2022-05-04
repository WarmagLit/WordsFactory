package com.tsu.wordsfactory.dictionary.ui.dictionary.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.ItemDefinitionBinding
import com.tsu.wordsfactory.databinding.ItemMeaningBinding
import com.tsu.wordsfactory.repository.model.Meaning

class MeaningAdapter : RecyclerView.Adapter<MeaningHolder>() {

    var meanings: List<Meaning> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningHolder {
        val itemBinding = ItemMeaningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MeaningHolder, position: Int) {
        val meaning = meanings[position]
        holder.bind(meaning)
    }

    override fun getItemCount(): Int = meanings.size
}

class MeaningHolder(private val itemBinding: ItemMeaningBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    private val adapter = DefinitionAdapter()

    init {
        itemBinding.recyclerMeanings.adapter = adapter
    }

    fun bind(meaning: Meaning) {
        itemBinding.textPart.text = meaning.partOfSpeech
        bindDefinitions(meaning)
    }

    private fun bindDefinitions(meaning: Meaning) {
        adapter.definitions = meaning.definitions
    }
}