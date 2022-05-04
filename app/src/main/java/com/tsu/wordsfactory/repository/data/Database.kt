package com.tsu.midtermexam3.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tsu.wordsfactory.repository.data.DefinitionModel
import com.tsu.wordsfactory.repository.data.MeaningModel


@androidx.room.Database(entities = [WordModel::class, MeaningModel::class, DefinitionModel::class], version = 1, exportSchema = false)

abstract class Database: RoomDatabase() {
    abstract fun wordsDao(): WordsDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "word"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }

    }
}