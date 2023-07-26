package com.pc.studyjapanesen5.presentation.viewcustom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.pc.studyjapanesen5.R

class CheckButton : AppCompatButton {

    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAtr: Int) : super(
        context, attrs, defStyleAtr
    ) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CheckButton)
        enabledBackground = typeArray.getDrawable(R.styleable.CheckButton_enabledBackground)
        disabledBackground = typeArray.getDrawable(R.styleable.CheckButton_disabledBackground)
        typeArray.recycle()

        updateBackground(isEnabled)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        updateBackground(enabled)
    }

    private fun updateBackground(enabled: Boolean) {
        background = if (enabled) enabledBackground else disabledBackground
    }
}