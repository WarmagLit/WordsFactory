package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.repository.model.Meaning

class MeaningAdapter : RecyclerView.Adapter<MeaningHolder>() {

    var meanings: List<Meaning> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meaning, parent, false)
        return MeaningHolder(view)
    }

    override fun onBindViewHolder(holder: MeaningHolder, position: Int) {
        val meaning = meanings[position]
        holder.bind(meaning)
    }

    override fun getItemCount(): Int = meanings.size
}

class MeaningHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter = DefinitionAdapter()

    private val speechPartText: TextView = itemView.findViewById(R.id.textPart)
    private val recyclerMeanings: RecyclerView = itemView.findViewById(R.id.recyclerMeanings)

    fun bind(meaning: Meaning) {
        recyclerMeanings.adapter = adapter
        speechPartText.text = meaning.partOfSpeech
        bindDefinitions(meaning)
    }

    private fun bindDefinitions(meaning: Meaning) {
        adapter.definitions = meaning.definitions
    }
}