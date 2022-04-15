package com.tsu.wordsfactory.repository

import com.tsu.midtermexam3.data.WordModel
import com.tsu.wordsfactory.repository.model.Word
import retrofit2.Response

interface WordsRepository {

    suspend fun addWord(word: Word)

    suspend fun deleteWord(word: Word)

    suspend fun getWord(word: String): List<Word>

    suspend fun getWordFromDB(word: String): List<Word>
}