package com.pc.studyjapanesen5.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pc.studyjapanesen5.domain.mapper.MapAbleToModel
import com.pc.studyjapanesen5.domain.model.VocabularyModel

@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "unit")
    val unit: Int,
    @ColumnInfo(name = "new_word")
    val newWord: String? = null,
    @ColumnInfo(name = "kanji")
    var kanji: String? = null,
    @ColumnInfo(name = "meaning")
    val wordMeaning: String? = null
) : MapAbleToModel<VocabularyModel> {

    constructor(vocabulary: VocabularyModel) : this(
        id = vocabulary.id,
        unit = vocabulary.unit,
        newWord = vocabulary.newWord,
        kanji = vocabulary.kanji,
        wordMeaning = vocabulary.wordMeaning
    )

    override fun toModel() = VocabularyModel(
        id = id,
        unit = unit,
        newWord = newWord,
        kanji = kanji,
        wordMeaning = wordMeaning
    )
}
