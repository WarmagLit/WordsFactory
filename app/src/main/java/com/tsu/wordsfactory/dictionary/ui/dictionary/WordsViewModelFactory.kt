package com.tsu.wordsfactory.dictionary.ui.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tsu.wordsfactory.network.Network
import com.tsu.wordsfactory.utils.NetworkUtils

class WordsViewModelFactory(private val networkUtils: NetworkUtils) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(Network.wordsRepository, networkUtils) as T
    }
}