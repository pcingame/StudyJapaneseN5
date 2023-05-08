package com.pc.studyjapanesen5.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pc.studyjapanesen5.model.AppDatabase.Companion.DATABASE_VERSION
import com.pc.studyjapanesen5.model.dao.AlphabetDao
import com.pc.studyjapanesen5.model.dao.WordDao
import com.pc.studyjapanesen5.model.entity.AlphabetEntity
import com.pc.studyjapanesen5.model.entity.WordEntity

@Database(
    entities = [WordEntity::class, AlphabetEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): WordDao
    abstract fun alphabetDao(): AlphabetDao

    companion object {
        private const val DB_NAME = "vocabulary_database"
        private const val ALPHABET_DB = "database/n5_jp_database.db"
        const val DATABASE_VERSION = 1

        fun build(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .createFromAsset(ALPHABET_DB)
                .build()
    }
}

