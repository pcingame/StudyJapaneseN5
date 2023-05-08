package com.pc.studyjapanesen5.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alphabet")
data class AlphabetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "type_character")
    val type: String,
    @ColumnInfo(name = "latin_character")
    val latin: String,
    @ColumnInfo(name = "hiragana_character")
    val hiragana: String,
    @ColumnInfo(name = "katakana_character")
    val katakana: String
)
