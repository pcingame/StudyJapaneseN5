package com.pc.studyjapanesen5.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordModel(
    val id: Int,
    val newWord: String,
    val pronounce: String,
    val wordMeaning: String
) : Parcelable
