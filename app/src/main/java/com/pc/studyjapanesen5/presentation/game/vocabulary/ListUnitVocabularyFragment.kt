package com.pc.studyjapanesen5.presentation.game.vocabulary

import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentListUnitVocabularyBinding
import com.pc.studyjapanesen5.databinding.ItemUnitVocabularyGameBinding
import com.pc.studyjapanesen5.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListUnitVocabularyFragment :
    BaseFragment<FragmentListUnitVocabularyBinding, HomeViewModel>(FragmentListUnitVocabularyBinding::inflate) {
    override val viewModel by viewModel<HomeViewModel>()

    private val unitListAdapter by lazy {
        SimpleListAdapter<ItemUnitVocabularyGameBinding, Int>(ItemUnitVocabularyGameBinding::inflate) { item, _ ->
            tvUnitGame.text = getString(R.string.unit_number, item.toString())
        }
    }

    override fun setupViews() {
        viewBinding.rcvUnitGame.adapter = unitListAdapter.apply {
            onItemClick = { _, position ->
                val bundle = VocabularyGameFragmentArgs(position + 1).toBundle()
                navigate(R.id.vocabularyGameFragment, bundle)
            }
        }
    }

    override fun initData() {
        viewModel.getAllVocabulary()
    }

    override fun observeData() {
        viewModel.groupUnit.observe(viewLifecycleOwner) {
            unitListAdapter.submitList(it)
        }
    }

}