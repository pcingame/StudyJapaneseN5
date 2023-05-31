package com.pc.studyjapanesen5.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    val mLoading = SingleLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        mLoading.value = false
    }

    protected val scope = viewModelScope.plus(exceptionHandler)

    /**
     * @param scope coroutine scope to execute task
     * @param onError the callback run when had error
     * @param onExecute the action to execute task
     * */
    protected fun <T> executeTask(
        scope: CoroutineScope = this.scope,
        onError: ((java.lang.Exception) -> Unit)? = null,
        onExecute: suspend () -> T
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                onExecute()
            } catch (e: Exception) {
                onError?.invoke(e) ?: throw e
            }
        }
    }

}
