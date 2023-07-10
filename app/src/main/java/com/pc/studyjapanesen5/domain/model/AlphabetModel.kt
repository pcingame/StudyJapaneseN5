package com.pc.studyjapanesen5.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlphabetModel(
    var id: Int,
    var type: String,
    var latin: String? = null,
    var hiragana: String? = null,
    var katakana: String? = null,
) : Parcelable
