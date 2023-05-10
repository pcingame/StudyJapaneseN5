package com.pc.studyjapanesen5.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlphabetModel(
    val id: Int,
    val type: String,
    val latin: String? = null,
    val hiragana: String? = null,
    val katakana: String? = null,
) : Parcelable
