package com.tsu.wordsfactory.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "definition")
data class DefinitionModel (
    @PrimaryKey
    val key: String,
    val meaningKey: String,
    val definition: String,
    val example: String?
)