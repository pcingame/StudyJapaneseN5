package com.pc.studyjapanesen5.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pc.studyjapanesen5.domain.mapper.MapAbleToModel
import com.pc.studyjapanesen5.domain.model.WordModel

@Entity(tableName = "vocabulary")
data class WordEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "new_word")
    val newWord: String,
    @ColumnInfo(name = "pronounce")
    var pronounce: String,
    @ColumnInfo(name = "meaning")
    val wordMeaning: String
) : MapAbleToModel<WordModel> {

    constructor(word: WordModel) : this(
        id = word.id,
        newWord = word.newWord,
        pronounce = word.pronounce,
        wordMeaning = word.wordMeaning
    )

    override fun toModel() = WordModel(
        id = id,
        newWord = newWord,
        pronounce = pronounce,
        wordMeaning = wordMeaning
    )
}
