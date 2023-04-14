package com.pc.studyjapanesen5.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.pc.studyjapanesen5.common.dialog.AlertNotice
import com.pc.studyjapanesen5.common.extension.ViewInflater
import com.pc.studyjapanesen5.databinding.ActivityBaseBinding

abstract class BaseActivity<VB : ViewBinding>(
    val inflateBinding: ViewInflater<VB>
) : AppCompatActivity() {
    protected lateinit var viewBinding: VB
    protected lateinit var baseBinding: ActivityBaseBinding

    private var isErrorDialogShowing = false

    /**
     * Override onCreate function of Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBaseBinding()
        setupViews()
        initData()
        observeData()
    }

    private fun initBaseBinding() {
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        viewBinding = inflateBinding(LayoutInflater.from(this), baseBinding.activityContent, true)
    }

    open fun setupViews() {}
    open fun initData() {}
    open fun observeData() {}

    /**
     * This function is used to show loading or not
     * @param isLoading is [Boolean] value
     */
    fun showLoading(isLoading: Boolean) {
        if (isLoading && baseBinding.activityError.isVisible) {
            baseBinding.activityError.isVisible = false
        }
        baseBinding.activityLoading.isVisible = isLoading
    }

    /**
     * This is the method to show error dialog
     */
    fun showErrorDialog(message: String, name: String? = null) {
        if (isErrorDialogShowing) {
            Log.d(Log.DEBUG.toString(), "skip show if showing $name")
            return
        }

        AlertNotice.show(this, message) {
            isErrorDialogShowing = false
        }
        isErrorDialogShowing = true
    }
}
