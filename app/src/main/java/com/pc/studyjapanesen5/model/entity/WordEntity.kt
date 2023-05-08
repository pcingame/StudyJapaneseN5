package com.pc.studyjapanesen5.model.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "new_word")
    val newWord: String,
    @ColumnInfo(name = "pronounce")
    var pronounce: String? = "",
    @ColumnInfo(name = "meaning")
    val wordMeaning: String
)
