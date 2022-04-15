package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.wordsfactory.repository.WordsRepository
import com.tsu.wordsfactory.repository.model.Word
import com.tsu.wordsfactory.utils.NetworkUtils
import com.tsu.wordsfactory.utils.SingleLiveEvent
import kotlinx.coroutines.*

class ListViewModel(
	private val repository: WordsRepository,
	private val networkUtils: NetworkUtils
) : ViewModel() {

	val words = MutableLiveData<List<Word>>()

	fun loadWord(word: String) {
		viewModelScope.launch {
			val wordsAnswer: List<Word>
			if (networkUtils.isOnline()) {
				wordsAnswer = repository.getWord(word)
			} else {
				wordsAnswer = repository.getWordFromDB(word)
			}
			Log.d("TAG", "We get response!")
			withContext(Dispatchers.Main) {
				words.postValue(wordsAnswer)
			}
		}
	}

	fun saveWord() {
		viewModelScope.launch {
		val word = words.value?.get(0)

			if (word != null) {
				repository.addWord(word)
			}
		}
	}

}