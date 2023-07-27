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


    override fun initDrawableStatePress(): Drawable? {
        TODO("Not yet implemented")
    }

    override fun initTextColorEnabled(): Int {
        TODO("Not yet implemented")
    }
}