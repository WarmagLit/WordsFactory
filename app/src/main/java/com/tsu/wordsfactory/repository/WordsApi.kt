package com.tsu.wordsfactory.repository

import com.tsu.wordsfactory.repository.model.Word
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WordsApi {
    @GET("{something}")
    suspend fun getWord(@Path("something") word: String): Response<List<Word>>
}