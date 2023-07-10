package com.pc.studyjapanesen5.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pc.studyjapanesen5.data.model.AppDatabase.Companion.DATABASE_VERSION
import com.pc.studyjapanesen5.data.model.dao.AlphabetDao
import com.pc.studyjapanesen5.data.model.dao.VocabularyDao
import com.pc.studyjapanesen5.data.model.entity.AlphabetEntity
import com.pc.studyjapanesen5.data.model.entity.VocabularyEntity

@Database(
    entities = [VocabularyEntity::class, AlphabetEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun alphabetDao(): AlphabetDao

    companion object {
        private const val DB_NAME = "vocabulary_database"
        private const val ALPHABET_DB = "database/n5_jp_db.db"
        const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun build(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .createFromAsset(ALPHABET_DB)
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

