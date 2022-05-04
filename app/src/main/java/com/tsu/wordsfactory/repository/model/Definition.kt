package com.tsu.wordsfactory.repository.model

data class Definition(
    val definition: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String?
)
