package com.tsu.wordsfactory.repository.model

data class Word (
        val word: String,
        val phonetic: String,
        val phonetics: List<Phonetic>,
        val meanings: List<Meaning>,
        val license: License?,
        val sourceUrls: List<String>
)


