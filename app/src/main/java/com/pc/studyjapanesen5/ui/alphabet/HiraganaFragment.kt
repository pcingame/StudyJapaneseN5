package com.pc.studyjapanesen5.ui.alphabet

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
import com.pc.studyjapanesen5.model.entity.AlphabetEntity


class HiraganaFragment :
    BaseFragment<FragmentHiraganaBinding, AlphabetViewModel>(FragmentHiraganaBinding::inflate) {
    override val viewModel: AlphabetViewModel by viewModels()

    private val hiraganaSingleAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetEntity>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.hiragana
            tvLatinHiraganaSingle.text = item.latin
        }
    }

    private val hiraganaDakuonAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetEntity>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.hiragana
            tvLatinHiraganaSingle.text = item.latin
        }
    }

    private val hiraganaComboAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetEntity>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.hiragana
            tvLatinHiraganaSingle.text = item.latin
        }
    }

    private val hiraganaSmallAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetEntity>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.hiragana
            tvLatinHiraganaSingle.text = item.latin
        }
    }

    private val hiraganaLongVowelAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetEntity>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.hiragana
            tvLatinHiraganaSingle.text = item.latin
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
        viewModel.getSingleHiragana(SINGLE_TYPE)
        viewModel.getDakuonHiragana(DAKUON_TYPE)
        viewModel.getComboHiragana(COMBO_TYPE)
        viewModel.getSmallHiragana(SMALL_TYPE)
        viewModel.getLongVowelHiragana(LONG_VOWEL_TYPE)
    }

    override fun observeData() {
        viewModel.hiraganaSingle.observe(viewLifecycleOwner) { alphabet ->
            hiraganaSingleAdapter.submitList(alphabet)
        }

        viewModel.hiraganaDakuon.observe(viewLifecycleOwner) { alphabet ->
            hiraganaDakuonAdapter.submitList(alphabet)
        }

        viewModel.hiraganaCombo.observe(viewLifecycleOwner) { alphabet ->
            hiraganaComboAdapter.submitList(alphabet)
        }

        viewModel.hiraganaSmall.observe(viewLifecycleOwner) { alphabet ->
            hiraganaSmallAdapter.submitList(alphabet)
        }

        viewModel.hiraganaLongVowel.observe(viewLifecycleOwner) { alphabet ->
            hiraganaLongVowelAdapter.submitList(alphabet)
        }
    }

}
