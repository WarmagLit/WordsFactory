package com.tsu.wordsfactory.app

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import com.tsu.midtermexam3.data.Database
import com.tsu.midtermexam3.data.NotesRepository
import com.tsu.wordsfactory.repository.WordsDatasourceImpl
import com.tsu.wordsfactory.repository.WordsRepository
import com.tsu.wordsfactory.repository.WordsRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class WordsApplication: Application() {
    private companion object {
        var BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }

    lateinit var wordsRepository: WordsRepository

    lateinit var retrofit: Retrofit

    fun createRepository(context: Context) {
        configureRetrofit()

        val wordsDao = Database.getDatabase(context).wordsDao()

        wordsRepository = WordsRepositoryImpl(
            WordsDatasourceImpl(retrofit.create()),
            wordsDao
        )
    }

    private fun configureRetrofit() {
        val okHttpClient =  buildHttpClient()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    private fun buildHttpClient(): OkHttpClient {
        val interceptor = buildInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun buildInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}