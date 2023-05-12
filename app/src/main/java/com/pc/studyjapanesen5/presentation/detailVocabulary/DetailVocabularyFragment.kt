package com.pc.studyjapanesen5.presentation.detailVocabulary

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentDetailVocabularyBinding
import com.pc.studyjapanesen5.databinding.ItemVocabularyBinding
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.presentation.home.HomeViewModel


class DetailVocabularyFragment :
    BaseFragment<FragmentDetailVocabularyBinding, HomeViewModel>(FragmentDetailVocabularyBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()
    private val args by navArgs<DetailVocabularyFragmentArgs>()

    private val detailVocabularyAdapter by lazy {
        SimpleListAdapter<ItemVocabularyBinding, VocabularyModel>(ItemVocabularyBinding::inflate) { item, _ ->
            tvVocabulary.text = item.newWord
            tvKanji.text = item.kanji
            tvWordMeaning.text = item.wordMeaning
        }
    }

    override fun setupViews() {
        viewBinding.layoutTitle.tvVocabulary.text = getString(R.string.hiragana)
        viewBinding.layoutTitle.tvKanji.text = getString(R.string.kanji)
        viewBinding.layoutTitle.tvWordMeaning.text = getString(R.string.meaning)
        viewBinding.rcvDetailVocabulary.adapter = detailVocabularyAdapter
    }

    override fun initData() {
        viewModel.getVocabularyByUnit(args.unit)
    }

    override fun observeData() {
        viewModel.detailVocabulary.observe(viewLifecycleOwner) { listVocabulary ->
            detailVocabularyAdapter.submitList(listVocabulary)
        }
    }

}
