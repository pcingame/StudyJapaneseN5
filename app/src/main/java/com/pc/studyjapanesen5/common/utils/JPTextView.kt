package com.pc.studyjapanesen5.common.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class JPTextView(context: Context, attributes: AttributeSet) :
    AppCompatTextView(context, attributes) {
    init {
        applyFont()
    }

    private fun applyFont() {
        val typeface: Typeface = Typeface.createFromAsset(context.assets, "font/jp_font.ttf")
        setTypeface(typeface)
    }
}