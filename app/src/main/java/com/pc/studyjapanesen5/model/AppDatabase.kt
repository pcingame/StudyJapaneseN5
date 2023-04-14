package com.pc.studyjapanesen5.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pc.studyjapanesen5.model.AppDatabase.Companion.DATABASE_VERSION

@Database(entities = [WordEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): WordDao

    companion object {
        private const val DB_NAME = "vocabulary_database"
        const val DATABASE_VERSION = 1

        fun build(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}

