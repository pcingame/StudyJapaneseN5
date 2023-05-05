package com.pc.studyjapanesen5.base.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.pc.studyjapanesen5.common.extension.ViewInflater

open class SimpleListAdapter<ItemBD : ViewBinding, T : Any>(
    private val onInflateItemBD: ViewInflater<ItemBD>,
    var onBind: ItemBD.(T, Int) -> Unit = { _, _ -> },
) : BaseListAdapter<T, BaseBindingHolder<ItemBD, T>>() {

    var delayClick = 200
    var onItemClick: ItemBD.(T, Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBindingHolder<ItemBD, T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = onInflateItemBD(layoutInflater, parent, false)
        return object : BaseBindingHolder<ItemBD, T>(viewBinding) {
            init {
                delayClick = this@SimpleListAdapter.delayClick
            }

            override fun bindData(itemData: T) {
                super.bindData(itemData)
                itemBD.onBind(itemData, adapterPosition)
            }

            override fun onHandleItemClick(mainItem: T) {
                itemBD.onItemClick(mainItem, adapterPosition)
            }
        }
    }

}
