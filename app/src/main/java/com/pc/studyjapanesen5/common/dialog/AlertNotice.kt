package com.pc.studyjapanesen5.common.dialog

import android.app.AlertDialog
import android.content.Context

object AlertNotice {

    private var isShowingAlert = false

    fun show(
        context: Context?,
        message: String,
        title: String? = null,
        buttonTextId: Int? = null,
        cancelTextId: Int? = null,
        callback: () -> Unit = {}
    ) {
        if (isShowingAlert || context == null) return

        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(buttonTextId ?: android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                callback.invoke()
            }
            cancelTextId?.let {
                setNeutralButton(it) { dialog, _ ->
                    dialog.dismiss()
                }
            }
        }

        // Create the AlertDialog
        val alertDialog = builder.create().apply {
            setCancelable(false)
            isShowingAlert = true
            kotlin.runCatching {
                show()
            }
        }
        alertDialog.setOnDismissListener {
            isShowingAlert = false
        }
    }
}
