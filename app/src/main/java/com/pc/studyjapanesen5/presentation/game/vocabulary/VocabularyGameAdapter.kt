package com.pc.studyjapanesen5.presentation.game.vocabulary

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
import com.pc.studyjapanesen5.databinding.ItemVocabularyGameAnswerBinding
import com.pc.studyjapanesen5.domain.model.VocabularyModel

class VocabularyGameAdapter(
    private val context: Context,
    private val answerType: Int,
    diffUtilItemCallBack: DiffUtil.ItemCallback<VocabularyModel> = BaseDiffUtilItemCallBack()
) : ListAdapter<VocabularyModel, VocabularyGameAdapter.ItemViewHolder>(diffUtilItemCallBack) {

    private var lastCheckPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemVocabularyGameAnswerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bindData(currentItem)
    }

    inner class ItemViewHolder(private val binding: ItemVocabularyGameAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                tvVocabularyAnswer.setOnClickListener {
                    onSingleItemUnSelected(binding)
                    notifyItemChanged(lastCheckPosition)
                    lastCheckPosition = adapterPosition
                }
            }
        }


        fun bindData(currentItem: VocabularyModel) {
            binding.apply {
                tvVocabularyAnswer.text = currentItem.wordMeaning
                if (adapterPosition != lastCheckPosition) {
                    onSingleItemSelected(binding)
                } else {
                    onSingleItemUnSelected(binding)
                }
            }
        }
    }

    private fun onSingleItemSelected(binding: ItemVocabularyGameAnswerBinding) = binding.apply {
        tvVocabularyAnswer.background =
            ContextCompat.getDrawable(context, R.drawable.background_answer_vocabulary_unselect)
    }

    private fun onSingleItemUnSelected(binding: ItemVocabularyGameAnswerBinding) = binding.apply {
        tvVocabularyAnswer.background =
            ContextCompat.getDrawable(context, R.drawable.background_answer_vocabulary_select)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<VocabularyModel>?) {
        if (list == currentList) {
            notifyDataSetChanged()
        }
        super.submitList(list ?: emptyList())
    }
}