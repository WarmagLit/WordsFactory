package com.tsu.wordsfactory.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.tsu.midtermexam3.data.WordModel
import com.tsu.midtermexam3.data.WordsDao
import com.tsu.wordsfactory.repository.data.DefinitionModel
import com.tsu.wordsfactory.repository.data.MeaningModel
import com.tsu.wordsfactory.repository.model.Definition
import com.tsu.wordsfactory.repository.model.Meaning
import com.tsu.wordsfactory.repository.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class WordsRepositoryImpl(private val wordsDatasource: WordsDatasource, private val wordsDao: WordsDao) : WordsRepository {

    override suspend fun addWord(word: Word) {
        val wordUuid = UUID.randomUUID().toString()
        val wordModel = WordModel(wordUuid, word.word, word.phonetic)
        for (meaning in word.meanings) {
            val meaningUuid = UUID.randomUUID().toString()
            val meaningModel = MeaningModel(meaningUuid, wordUuid, meaning.partOfSpeech)
            wordsDao.addMeaning(meaningModel)
            for (def in meaning.definitions) {
                val definitionUuid = UUID.randomUUID().toString()
                val definitionModel = DefinitionModel(definitionUuid, meaningUuid, def.definition, def.example)
                wordsDao.addDefinition(definitionModel)
            }
        }

        wordsDao.addWord(wordModel)
    }

    override suspend fun deleteWord(word: Word) {
        TODO("Not yet implemented")
    }

    //val wordsList: LiveData<List<WordModel>> = wordsDao.getAllWords()



    override suspend fun getWord(word: String): List<Word> {
        val response: Response<List<Word>> = wordsDatasource.getWord(word)

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        }
        return emptyList()
    }

    override suspend fun getWordFromDB(word: String): List<Word> =
        withContext(Dispatchers.IO) {
            val wordModel = wordsDao.getWord(word)
            if (wordModel== null) {
                return@withContext emptyList()
            }
            val meaningsModel = wordsDao.getMeaning(wordModel.key)
            val definitionsModel = wordsDao.getDefinitions(meaningsModel.key)

            val definitions = mutableListOf<Definition>()
            for (def in definitionsModel) {
                definitions.add(Definition(def.definition, listOf(), listOf(), def.example))
            }

            val meaning = Meaning(meaningsModel.partOfSpeech, definitions, listOf(), listOf())
            return@withContext listOf(
                Word(
                    wordModel.word,
                    wordModel.phonetic,
                    listOf(),
                    listOf(meaning),
                    null,
                    listOf()
                )
            )
        }

}