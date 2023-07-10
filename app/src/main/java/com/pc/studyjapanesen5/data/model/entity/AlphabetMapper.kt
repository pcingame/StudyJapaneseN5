package com.pc.studyjapanesen5.data.model.entity

import com.pc.studyjapanesen5.domain.model.AlphabetModel

class AlphabetMapper : DataMapper<AlphabetEntity, AlphabetModel> {
    override fun toModel(entity: AlphabetEntity): AlphabetModel {
        return AlphabetModel(
            id = entity.id,
            type = entity.type,
            latin = entity.latin,
            hiragana = entity.hiragana,
            katakana = entity.katakana
        )
    }

    override fun toEntity(model: AlphabetModel): AlphabetEntity {
        return AlphabetEntity(
            id = model.id,
            type = model.type,
            latin = model.latin,
            hiragana = model.hiragana,
            katakana = model.katakana
        )
    }
}