package com.pc.studyjapanesen5.presentation.game.alphabet

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseDiffUtilItemCallBack
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.databinding.ItemAlphabetAnswerBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel

class AlphabetGameAdapter(
    private val context: Context,
    private val answerType: String,
    diffUtilItemCallBack: DiffUtil.ItemCallback<AlphabetModel> = BaseDiffUtilItemCallBack()
) : ListAdapter<AlphabetModel, AlphabetGameAdapter.ItemViewHolder>(diffUtilItemCallBack) {

    private var lastCheckPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAlphabetAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bindData(currentItem)
    }

    inner class ItemViewHolder(private val binding: ItemAlphabetAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.apply {
                    layoutItemAnswer.setOnClickListener {
                        onSingleItemUnSelected(binding)
                        notifyItemChanged(lastCheckPosition)
                        lastCheckPosition = adapterPosition
                    }
                }
            }


            fun bindData(currentItem: AlphabetModel) {
                binding.apply {
                    when (answerType) {
                        Constant.AlphabetType.HIRAGANA_TYPE -> tvAlphabetAnswer.text = currentItem.hiragana
                        Constant.AlphabetType.KATAKANA_TYPE -> tvAlphabetAnswer.text = currentItem.katakana
                        else -> tvAlphabetAnswer.text = currentItem.katakana
                    }
                    if (adapterPosition != lastCheckPosition) {
                        onSingleItemSelected(binding)
                    } else {
                        onSingleItemUnSelected(binding)
                    }
                }
            }
    }

    private fun onSingleItemSelected(binding: ItemAlphabetAnswerBinding) = binding.apply {
        layoutItemAnswer.background = ContextCompat.getDrawable(context, R.drawable.background_answer_alphabet_unselect)
    }

    private fun onSingleItemUnSelected(binding: ItemAlphabetAnswerBinding) = binding.apply {
        layoutItemAnswer.background = ContextCompat.getDrawable(context, R.drawable.background_answer_alphabet_select)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<AlphabetModel>?) {
        if (list == currentList) {
            notifyDataSetChanged()
        }
        super.submitList(list ?: emptyList())
    }
}