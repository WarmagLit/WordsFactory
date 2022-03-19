package com.tsu.wordsfactory.dictionary.ui.dictionary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DictionaryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dict Fragment"
    }
    val text: LiveData<String> = _text
}