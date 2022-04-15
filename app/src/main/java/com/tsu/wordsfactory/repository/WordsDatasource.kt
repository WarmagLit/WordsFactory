package com.tsu.wordsfactory.repository

import android.util.Log
import com.tsu.wordsfactory.repository.model.Word
import retrofit2.Response

interface WordsDatasource {
    suspend fun getWord(word: String): Response<List<Word>>
}


class WordsDatasourceImpl(private val wordsApi: WordsApi, ) : WordsDatasource {

    private val words = mutableListOf<Word>()

    override suspend fun getWord(word: String): Response<List<Word>> {
        Log.d("Tag", "FROM API...")
        return wordsApi.getWord(word)
    }

}