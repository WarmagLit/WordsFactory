package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsu.wordsfactory.repository.WordsRepository
import com.tsu.wordsfactory.repository.model.Word
import com.tsu.wordsfactory.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
				if (wordsAnswer.isEmpty()) return@launch

				var wordPhonetic = wordsAnswer.first().phonetics.first()
				
				// find audio url in phonetics
				for (phonetic in wordsAnswer.first().phonetics) {
					if (phonetic.audio != "") {
						wordPhonetic = phonetic
						break
					}
				}
				val savedUri = Uri.parse(wordPhonetic.audio)
				wordsAnswer.first().phonetics.first().audioUri = savedUri
			} else {
				wordsAnswer = repository.getWordFromDB(word)
			}
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