package com.tsu.midtermexam3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tsu.wordsfactory.repository.model.Meaning
import java.sql.Time

@Entity(tableName = "word")
data class WordModel(
    @PrimaryKey
    val key: String,
    val word: String,
    val phonetic: String
)
