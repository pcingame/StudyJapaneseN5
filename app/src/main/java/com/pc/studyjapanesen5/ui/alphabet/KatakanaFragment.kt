package com.pc.studyjapanesen5.ui.alphabet

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.databinding.FragmentKatakanaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetBinding
import com.pc.studyjapanesen5.model.entity.AlphabetEntity

class KatakanaFragment :
    BaseFragment<FragmentKatakanaBinding, AlphabetViewModel>(FragmentKatakanaBinding::inflate) {

    override val viewModel: AlphabetViewModel by viewModels()

    private val hiraganaSingleAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetEntity>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.katakana
            tvLatinHiraganaSingle.text = item.latin
        }
    }

    override fun setupViews() {
        viewBinding.layoutHiraganaSingle.tvLearn.text =
            getString(R.string.learn, getString(R.string.katakana))
        viewBinding.layoutHiraganaSingle.rcvSingle.adapter = hiraganaSingleAdapter
    }

    override fun initData() {
        viewModel.getSingleHiragana(Constant.SINGLE_TYPE)
    }

    override fun observeData() {
        viewModel.hiraganaSingle.observe(viewLifecycleOwner) { alphabet ->
            hiraganaSingleAdapter.submitList(alphabet)
        }
    }

}
