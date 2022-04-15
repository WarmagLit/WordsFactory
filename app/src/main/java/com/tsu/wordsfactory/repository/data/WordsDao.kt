package com.tsu.midtermexam3.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsu.wordsfactory.repository.data.DefinitionModel
import com.tsu.wordsfactory.repository.data.MeaningModel

@Dao
interface WordsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: WordModel)
    /*
    @Query("DELETE FROM word WHERE text = :text AND time = :time ")
    suspend fun deleteWord(text: String, time: String)
    */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeaning(meaningModel: MeaningModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDefinition(definitionModel: DefinitionModel)

    @Query("SELECT * FROM word")
    fun getAllWords(): LiveData<List<WordModel>>

    @Query("SELECT * FROM word WHERE word.word =:text")
    fun getWord(text: String): WordModel

    @Query("SELECT * FROM meaning WHERE meaning.wordKey =:key")
    fun getMeaning(key: String): MeaningModel

    @Query("SELECT * FROM definition WHERE definition.meaningKey =:key")
    fun getDefinitions(key: String): List<DefinitionModel>
}