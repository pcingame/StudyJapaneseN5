package com.pc.studyjapanesen5.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VocabularyModel(
    val id: Int,
    val unit: Int,
    val newWord: String? = null,
    val kanji: String? = null,
    val wordMeaning: String? = null,
) : Parcelable {

}


