package com.pc.studyjapanesen5.presentation.game.vocabulary

import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.Constant.VocabularyType.ALL_TYPE
import com.pc.studyjapanesen5.databinding.FragmentListUnitVocabularyBinding
import com.pc.studyjapanesen5.databinding.ItemUnitVocabularyGameBinding
import com.pc.studyjapanesen5.presentation.game.GameFragment
import com.pc.studyjapanesen5.presentation.game.result.ResultFragment.Companion.KEY_RESULT_VOCABULARY
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
        val typeFromGameFragment =
            activity?.intent?.extras?.getInt(GameFragment.VOCABULARY_GAME) ?: ALL_TYPE
        val typeFromResultFragment =
            activity?.intent?.extras?.getInt(KEY_RESULT_VOCABULARY) ?: ALL_TYPE
        when {
            (typeFromGameFragment == ALL_TYPE || typeFromResultFragment == ALL_TYPE) -> {
                val bundle = VocabularyGameFragmentArgs(ALL_TYPE).toBundle()
                navigate(R.id.vocabularyGameFragment, bundle)
            }

            (typeFromResultFragment in 1..25) -> {
                val bundle = VocabularyGameFragmentArgs(typeFromResultFragment).toBundle()
                navigate(R.id.vocabularyGameFragment, bundle)
            }

            else -> {
                //do not thing
            }
        }
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