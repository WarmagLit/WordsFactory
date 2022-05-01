package com.tsu.wordsfactory.dictionary.ui.dictionary

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
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
				val savedUri = downloadAudio(wordsAnswer.first().phonetics.first().audio)
				Log.d("Log", savedUri.toString())
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

	private fun downloadAudio(url: String): Uri? {
		val uri = Uri.parse(url)

		if(url == "") {
			return null
		}
		val request = DownloadManager.Request(uri)
		val saveUri = networkUtils.getDir()
		Log.d("save", saveUri.toString())
		//request.setDestinationUri(saveUri)
		request.setTitle("File")
		request.setDescription("Downloading...")
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
		request.setAllowedOverMetered(true)
		val downloadManager= networkUtils.getDownloadManager()
		val downloadID = downloadManager.enqueue(request)

		val savedDir = downloadManager.getUriForDownloadedFile(downloadID)
		return savedDir
	}

}