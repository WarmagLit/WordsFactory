package com.tsu.wordsfactory.repository

import com.tsu.midtermexam3.data.WordsDao
import com.tsu.wordsfactory.repository.data.WordDBTransfer
import com.tsu.wordsfactory.repository.model.Word
import retrofit2.Response

class WordsRepositoryImpl(private val wordsDatasource: WordsDatasource, private val wordsDao: WordsDao) : WordsRepository {

    override suspend fun addWord(word: Word) {
        WordDBTransfer.addWordToDatabase(word, wordsDao)
    }

    override suspend fun getWord(word: String): List<Word> {
        val response: Response<List<Word>> = wordsDatasource.getWord(word)

        if (response.isSuccessful && response.body() != null) {
            return response.body()?: emptyList()
        }
        return emptyList()
    }

    override suspend fun getWordFromDB(word: String): List<Word> =
        WordDBTransfer.getWordFromDatabase(word, wordsDao)

}