package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tsu.wordsfactory.app.WordsApplication
import com.tsu.wordsfactory.utils.NetworkUtils

class WordsViewModelFactory(private val application: Application, private val networkUtils: NetworkUtils) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel((application as WordsApplication).wordsRepository, networkUtils) as T
    }
}