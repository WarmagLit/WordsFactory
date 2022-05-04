package com.tsu.wordsfactory.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tsu.wordsfactory.repository.model.Definition

@Entity(tableName = "meaning")
data class MeaningModel(
    @PrimaryKey
    val key: String,
    val wordKey: String,
    val partOfSpeech: String
)
