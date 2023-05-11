package com.pc.studyjapanesen5.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.databinding.ItemUnitBinding
import com.pc.studyjapanesen5.databinding.ItemVocabularyBinding
import com.pc.studyjapanesen5.domain.model.VocabularyModel

class VocabularyExpandableListAdapter(
    private val context: Context,
    private val groupTitles: List<Int>,
    private val itemData: MutableMap<Int, List<VocabularyModel>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return groupTitles.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return itemData[groupTitles[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupTitles[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): VocabularyModel {
        return itemData[groupTitles[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds() = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val unitBinding = ItemUnitBinding.inflate(LayoutInflater.from(context), parent, false)
        val unit = getGroup(groupPosition).toString()
        unitBinding.tvUnit.text = context.getString(R.string.unit, unit)
        return unitBinding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val vocabularyBinding =
            ItemVocabularyBinding.inflate(LayoutInflater.from(context), parent, false)
        val vocabularyModel = getChild(groupPosition, childPosition)
        vocabularyBinding.tvVocabulary.text = vocabularyModel.newWord.toString()
        vocabularyBinding.tvKanji.text = vocabularyModel.kanji ?: ""
        vocabularyBinding.tvWordMeaning.text = vocabularyModel.wordMeaning.toString()
        return vocabularyBinding.root
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = false
}
