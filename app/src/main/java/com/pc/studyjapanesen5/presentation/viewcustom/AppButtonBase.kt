package com.pc.studyjapanesen5.presentation.viewcustom

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.di.App

abstract class AppButtonBase : AppCompatButton {

    companion object {
        private const val ANIMATE_DURATION = 200
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews(context, attrs)
    }

    private var mDrawableEnableState: Drawable? = initDrawableStateEnabled()
    private var mDrawableDisabledState: Drawable? = initDrawableStateDisabled()
    private var mDrawableHighlightState: Drawable? = initDrawableStatePress()

    private var disableToEnableTransition = TransitionDrawable(arrayOf(mDrawableDisabledState, mDrawableEnableState))
    private var enableToHighlightTransition = TransitionDrawable(arrayOf(mDrawableEnableState, mDrawableHighlightState))

    private var lastState: Boolean? = null
    private var inClickSession: Boolean = false

    private var withAnimation = true
    private var clickListener: OnClickListener? = null

    private var mTextColorEnable: Int = ContextCompat.getColor(App.context, R.color.white)
    private var mTextColorDisable: Int = ContextCompat.getColor(App.context, R.color.black)

    private fun initViews(context: Context?, attrs: AttributeSet?) {
        val typedArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.AppButton, 0, 0)
        mTextColorEnable = ContextCompat.getColor(App.context, initTextColorEnabled())
        mTextColorDisable = ContextCompat.getColor(App.context, initTextColorDisabled())
        getValueAttributes(typedArray)
        gravity = Gravity.CENTER
        lastState = isEnabled

        context?.let {
            var textColor = mTextColorEnable
            if (!isEnabled) {
                textColor = mTextColorDisable
            }
            setTextColor(textColor)
        }
        initTransition()
    }

    private fun getValueAttributes(typedArray: TypedArray?) {
        if (typedArray != null) {

            if (typedArray.hasValue(R.styleable.AppButton_enabledBackground)) {
                mDrawableEnableState = typedArray.getDrawable(R.styleable.AppButton_enabledBackground)
            }
            if (typedArray.hasValue(R.styleable.AppButton_disabledBackground)) {
                mDrawableDisabledState = typedArray.getDrawable(R.styleable.AppButton_disabledBackground)
            }
            if (typedArray.hasValue(R.styleable.AppButton_highlightBackground)) {
                mDrawableHighlightState = typedArray.getDrawable(R.styleable.AppButton_highlightBackground)
            }
            if ((typedArray.hasValue(R.styleable.AppButton_enabledBackground)
                        && !typedArray.hasValue(R.styleable.AppButton_highlightBackground))
            ) {
                mDrawableHighlightState = typedArray.getDrawable(R.styleable.AppButton_enabledBackground)
            }
            if ((!typedArray.hasValue(R.styleable.AppButton_enabledBackground)
                        && typedArray.hasValue(R.styleable.AppButton_highlightBackground))
            ) {
                mDrawableEnableState = typedArray.getDrawable(R.styleable.AppButton_enabledBackground)
            }
            if (typedArray.hasValue(R.styleable.AppButton_textColorEnable)) {
                mTextColorEnable = typedArray.getColor(
                    R.styleable.AppButton_textColorEnable, ContextCompat.getColor(App.context, initTextColorEnabled())
                )
            }
            if (typedArray.hasValue(R.styleable.AppButton_textColorDisable)) {
                mTextColorDisable = typedArray.getColor(
                    R.styleable.AppButton_textColorDisable, ContextCompat.getColor(App.context, initTextColorDisabled())
                )
            }
        }
    }

    private fun initTransition() {
        background = if (isEnabled) mDrawableEnableState else mDrawableDisabledState
        disableToEnableTransition = TransitionDrawable(arrayOf(mDrawableDisabledState, mDrawableEnableState))
        enableToHighlightTransition = TransitionDrawable(arrayOf(mDrawableEnableState, mDrawableHighlightState))
    }

    abstract fun initDrawableStateEnabled(): Drawable?

    abstract fun initDrawableStateDisabled(): Drawable?

    abstract fun initDrawableStatePress(): Drawable?

    abstract fun initTextColorEnabled(): Int

    private fun initTextColorDisabled(): Int {
        return R.color.black
    }

    override fun setEnabled(enabled: Boolean) {
        if (getWithAnimation()) {
            super.setEnabled(enabled)
            if (lastState != null && lastState != enabled) {
                if (enabled) {
                    setTextColorWithAnimation(mTextColorDisable, mTextColorEnable)
                    background = disableToEnableTransition
                    disableToEnableTransition.startTransition(ANIMATE_DURATION)
                } else {
                    setTextColorWithAnimation(mTextColorEnable, mTextColorDisable)
                    background = disableToEnableTransition
                    disableToEnableTransition.startTransition(0)
                    disableToEnableTransition.reverseTransition(ANIMATE_DURATION)
                }
                lastState = enabled
            } else {
                super.setEnabled(enabled)
                if (lastState != null && lastState != enabled) {
                    background = if (enabled) {
                        setTextColor(mTextColorEnable)
                        mDrawableEnableState
                    } else {
                        setTextColor(mTextColorDisable)
                        mDrawableDisabledState
                    }
                    lastState = enabled
                }
            }
        }
    }

//     private fun setEnableWithoutAnimation(enable: Boolean) {
//        setWithAnimation(false)
//        isEnabled = enable
//        setWithAnimation(true)
//    }

    private fun setTextColorWithAnimation(fromColor: Int, toColor: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)
        colorAnimation.duration = ANIMATE_DURATION.toLong()
        colorAnimation.addUpdateListener {
            setTextColor(it.animatedValue as Int)
        }
        colorAnimation.start()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) return false
        if (!inClickSession) {
            background = enableToHighlightTransition
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                inClickSession = true
                enableToHighlightTransition.startTransition(ANIMATE_DURATION)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                inClickSession = false
                enableToHighlightTransition.reverseTransition(ANIMATE_DURATION)
                if (event.action != MotionEvent.ACTION_CANCEL) {
                    clickListener?.onClick(this)
                }
            }
        }
        return true
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        clickListener = listener
    }

//    private fun setWithAnimation(withAnimation: Boolean) {
//        this.withAnimation = withAnimation
//    }

    private fun getWithAnimation(): Boolean {
        return this.withAnimation
    }
}