package com.tsu.wordsfactory.dictionary.ui.dictionary.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.net.VpnService.prepare
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsu.wordsfactory.R
import com.tsu.wordsfactory.databinding.ItemMeaningBinding
import com.tsu.wordsfactory.databinding.ItemWordBinding
import com.tsu.wordsfactory.repository.model.Word
import com.tsu.wordsfactory.utils.NetworkUtils
import kotlinx.coroutines.NonCancellable.start

class WordsAdapter(private val networkUtils: NetworkUtils) : RecyclerView.Adapter<WordHolder>() {

    var words: List<Word> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val itemBinding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordHolder(itemBinding, networkUtils)
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val character = words[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = words.size
}

class WordHolder(private val itemBinding: ItemWordBinding, private val networkUtils: NetworkUtils) : RecyclerView.ViewHolder(itemBinding.root) {

    private val adapter = MeaningAdapter()

    fun bind(word: Word) {
        itemBinding.textWord.text = word.word
        itemBinding.textPhonetic.text = word.phonetic
        itemBinding.recyclerPartMeaning.adapter = adapter

        itemBinding.btnPlayAudio.setOnClickListener {
            Log.d("f", "Play")
            val uri = word.phonetics.first().audioUri
            Log.d("Uri", uri.toString())
            if (uri != null)
                networkUtils.playContentUri(uri)
            else
                Log.d("Tag", "Empty Uri!!!")
            //RingtoneManager.getRingtone(cxt, word.phonetics.first().audioUri).play()
        }

        bindMeaning(word)
    }

    private fun bindMeaning(word: Word) {
        adapter.meanings = word.meanings
    }
}
