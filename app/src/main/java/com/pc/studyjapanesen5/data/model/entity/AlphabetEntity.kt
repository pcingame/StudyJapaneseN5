package com.pc.studyjapanesen5.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pc.studyjapanesen5.domain.mapper.MapAbleToModel
import com.pc.studyjapanesen5.domain.model.AlphabetModel

@Entity(tableName = "alphabet")
data class AlphabetEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "type_character")
    val type: String,
    @ColumnInfo(name = "latin_character")
    val latin: String? = "",
    @ColumnInfo(name = "hiragana_character")
    val hiragana: String? = "",
    @ColumnInfo(name = "katakana_character")
    val katakana: String? = "",
) : MapAbleToModel<AlphabetModel> {
    constructor(alphabet: AlphabetModel) : this(
        id = alphabet.id,
        type = alphabet.type,
        latin = alphabet.latin,
        hiragana = alphabet.hiragana,
        katakana = alphabet.katakana
    )

    override fun toModel() = AlphabetModel(
        id = id,
        type = type,
        latin = latin,
        hiragana = hiragana,
        katakana = katakana
    )
}
