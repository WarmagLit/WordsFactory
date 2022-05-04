package com.tsu.wordsfactory.repository.model

import android.net.Uri

data class Phonetic(
    val text: String,
    val audio: String,
    var audioUri: Uri? = null
)
