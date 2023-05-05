package com.pc.studyjapanesen5.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pc.studyjapanesen5.common.extension.click

open class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    protected var itemData: T? = null
    var delayClick = 250

    init {
        itemView.click(delayClick) {
            itemData?.let(::onHandleItemClick)
        }
    }

    open fun bindData(itemData: T){
        this.itemData = itemData
    }

    open fun onHandleItemClick(mainItem: T){

    }
}

open class BaseBindingHolder<ItemBD : ViewBinding, T>(
    protected val itemBD: ItemBD
) : BaseViewHolder<T>(itemBD.root)
