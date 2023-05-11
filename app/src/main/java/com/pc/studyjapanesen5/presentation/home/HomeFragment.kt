package com.pc.studyjapanesen5.presentation.home

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentHomeBinding
import com.pc.studyjapanesen5.domain.model.VocabularyModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()
    private lateinit var vocabularyExpandableListAdapter: VocabularyExpandableListAdapter
    private lateinit var groupUnit: List<Int>
    private lateinit var itemVocabulary: MutableMap<Int, List<VocabularyModel>>

    override fun setupViews() {
    }

    override fun initData() {
        viewModel.getAllVocabulary()
    }

    override fun observeData() {
        viewModel.itemVocabulary.observe(viewLifecycleOwner) { vocabularyMap ->
            itemVocabulary = vocabularyMap
        }

        viewModel.groupUnit.observe(viewLifecycleOwner) { unitList ->
            groupUnit = unitList
            vocabularyExpandableListAdapter =
                VocabularyExpandableListAdapter(requireContext(), groupUnit, itemVocabulary)
            viewBinding.expandableListViewVocabulary.setAdapter(vocabularyExpandableListAdapter)
        }

    }
}
