package com.tsu.wordsfactory.repository

import android.util.Log
import com.tsu.wordsfactory.repository.model.Word
import retrofit2.Response

interface WordsDatasource {
    suspend fun getWord(word: String): Response<List<Word>>
}


class WordsDatasourceImpl(private val wordsApi: WordsApi, ) : WordsDatasource {

    override suspend fun getWord(word: String): Response<List<Word>> {
        return wordsApi.getWord(word)
    }

}