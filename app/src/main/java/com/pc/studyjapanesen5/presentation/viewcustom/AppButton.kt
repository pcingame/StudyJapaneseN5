package com.pc.studyjapanesen5.presentation.viewcustom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.pc.studyjapanesen5.R

class AppButton : AppButtonBase {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAtr: Int) : super(
        context, attrs, defStyleAtr
    )

    override fun initDrawableStateEnabled(): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.background_button_choose_answer)
    }

    override fun initDrawableStateDisabled(): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.button_disabled_default)
    }

    override fun initDrawableStatePress(): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.button_highlighted_default)
    }

    override fun initTextColorEnabled(): Int {
        return  R.color.white
    }
}