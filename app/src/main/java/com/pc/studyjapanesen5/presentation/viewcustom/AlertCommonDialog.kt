package com.pc.studyjapanesen5.presentation.viewcustom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.LayoutDialogBinding

class AlertCommonDialog(
    context: Context,
    private var binding: LayoutDialogBinding? = null,
    private var clickYesListener: ((dialog: Dialog) -> Unit)? = null,
    private var clickNoListener: ((dialog: Dialog) -> Unit)? = null,
    private var clickYesSystemListener: ((dialog: Dialog) -> Unit)? = null
) : Dialog(context) {

    private var stringContent: String? = null
    private var stringYes: String? = null
    private var stringCancel: String? = null
    private var contentTextSize: Float? = null
    private var isGameDialog = true

    companion object {
        private const val contentTextSizeDefault = 24f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        binding = LayoutDialogBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setCancelable(false)
        initListener()
    }

    private fun initListener() {
        binding?.btnDialogNo?.click {
            if (clickNoListener != null) clickNoListener?.invoke(this) else dismiss()
        }

        binding?.btnDialogYes?.click {
            if (clickYesListener != null) clickYesListener?.invoke(this) else dismiss()
        }

        binding?.layoutButtonDialog?.click {
            if (clickYesSystemListener != null) clickYesSystemListener?.invoke(this) else dismiss()
        }
    }

    fun setOnClickYesListener(cb: (dialog: Dialog) -> Unit): AlertCommonDialog {
        this.clickYesListener = cb
        return this
    }

    fun setOnClickNoListener(cb: (dialog: Dialog) -> Unit): AlertCommonDialog {
        this.clickNoListener = cb
        return this
    }

    fun setOnClickYesSystemListener(cb: (dialog: Dialog) -> Unit): AlertCommonDialog {
        this.clickYesSystemListener = cb
        return this
    }

    fun setContent(content: Int): AlertCommonDialog {
        this.stringContent = context.getString(content)
        return this
    }

    fun setTextButtonYes(text: Int): AlertCommonDialog {
        this.stringYes = context.getString(text)
        return this
    }

    fun setTextButtonNo(text: Int): AlertCommonDialog {
        this.stringCancel = context.getString(text)
        return this
    }

    fun setContentTextSize(textSize: Float): AlertCommonDialog {
        this.contentTextSize = textSize
        return this
    }

    fun isGameDialog(isGame: Boolean): AlertCommonDialog {
        this.isGameDialog = isGame
        return this
    }

    override fun show() {
        super.show()
        bindData()
        window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.background_dialog_exit
            )
        )
        val displayMetrics = context.resources.displayMetrics
        val dialogWidth = (displayMetrics.widthPixels * 0.95).toInt()
        window?.setLayout(dialogWidth, LinearLayout.LayoutParams.WRAP_CONTENT)
        val attr = window?.attributes
        //attr?.windowAnimations = R.style.animation
        window?.attributes = attr
    }

    private fun bindData() {
        binding?.tvDialog?.setTextSize(
            TypedValue.COMPLEX_UNIT_SP,
            contentTextSize ?: contentTextSizeDefault
        )
        if (isGameDialog) {
            binding?.layoutButtonDialog?.visibility = View.GONE
            binding?.layoutButtonGame?.visibility = View.VISIBLE
        } else {
            binding?.layoutButtonDialog?.visibility = View.VISIBLE
            binding?.layoutButtonGame?.visibility = View.GONE
            binding?.layoutButtonDialog?.text = context.getString(R.string.understand)
        }
        binding?.tvDialog?.text = stringContent ?: context.getString(R.string.dialog_mess)
        binding?.btnDialogNo?.text = stringCancel ?: context.getString(R.string.dialog_yes)
        binding?.btnDialogYes?.text = stringYes ?: context.getString(R.string.dialog_no)
    }
}