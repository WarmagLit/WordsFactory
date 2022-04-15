package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.repository.model.Word

class WordsAdapter : RecyclerView.Adapter<WordHolder>() {

    var words: List<Word> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return WordHolder(view)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val character = words[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = words.size
}

class WordHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter = MeaningAdapter()

    private val wordText: TextView = itemView.findViewById(R.id.textWord)
    private val phoneticText: TextView = itemView.findViewById(R.id.textPhonetic)
    private val recyclerPartMeaning: RecyclerView = itemView.findViewById(R.id.recyclerPartMeaning)

    fun bind(word: Word) {
        wordText.text = word.word
        phoneticText.text = word.phonetic

        recyclerPartMeaning.adapter = adapter
        bindMeaning(word)
    }

    fun bindMeaning(word: Word) {
        adapter.meanings = word.meanings
    }
}