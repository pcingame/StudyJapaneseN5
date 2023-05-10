package com.pc.studyjapanesen5.presentation.alphabet

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.Constant.COMBO_TYPE
import com.pc.studyjapanesen5.common.Constant.DAKUON_TYPE
import com.pc.studyjapanesen5.common.Constant.LONG_VOWEL_TYPE
import com.pc.studyjapanesen5.common.Constant.SINGLE_TYPE
import com.pc.studyjapanesen5.common.Constant.SMALL_TYPE
import com.pc.studyjapanesen5.databinding.FragmentHiraganaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel


class HiraganaFragment :
    BaseFragment<FragmentHiraganaBinding, AlphabetViewModel>(FragmentHiraganaBinding::inflate) {
    override val viewModel: AlphabetViewModel by viewModels()

    private val hiraganaSingleAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.hiragana
            tvLatinSingle.text = item.latin
        }
    }

    private val hiraganaDakuonAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.hiragana
            tvLatinSingle.text = item.latin
        }
    }

    private val hiraganaComboAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.hiragana
            tvLatinSingle.text = item.latin
        }
    }

    private val hiraganaSmallAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.hiragana
            tvLatinSingle.text = item.latin
        }
    }

    private val hiraganaLongVowelAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.hiragana
            tvLatinSingle.text = item.latin
        }
    }

    override fun setupViews() {
        viewBinding.layoutHiraganaSingle.tvLearn.text =
            getString(R.string.learn, getString(R.string.hiragana))
        viewBinding.layoutHiraganaSingle.rcvSingle.adapter = hiraganaSingleAdapter

        viewBinding.layoutHiraganaDakuon.rcvSingle.adapter = hiraganaDakuonAdapter

        viewBinding.layoutHiraganaCombo.rcvSingle.adapter = hiraganaComboAdapter

        viewBinding.layoutHiraganaSmall.rcvSingle.adapter = hiraganaSmallAdapter

        viewBinding.layoutHiraganaLongVowel.rcvSingle.adapter = hiraganaLongVowelAdapter
    }

    override fun initData() {
        viewModel.getSingle(SINGLE_TYPE)
        viewModel.getDakuon(DAKUON_TYPE)
        viewModel.getCombo(COMBO_TYPE)
        viewModel.getSmall(SMALL_TYPE)
        viewModel.getLongVowel(LONG_VOWEL_TYPE)
    }

    override fun observeData() {
        viewModel.alphabetSingle.observe(viewLifecycleOwner) { alphabet ->
            hiraganaSingleAdapter.submitList(alphabet)
        }

        viewModel.alphabetDakuon.observe(viewLifecycleOwner) { alphabet ->
            hiraganaDakuonAdapter.submitList(alphabet)
        }

        viewModel.alphabetCombo.observe(viewLifecycleOwner) { alphabet ->
            hiraganaComboAdapter.submitList(alphabet)
        }

        viewModel.alphabetSmall.observe(viewLifecycleOwner) { alphabet ->
            hiraganaSmallAdapter.submitList(alphabet)
        }

        viewModel.alphabetLongVowel.observe(viewLifecycleOwner) { alphabet ->
            hiraganaLongVowelAdapter.submitList(alphabet)
        }
    }

}
