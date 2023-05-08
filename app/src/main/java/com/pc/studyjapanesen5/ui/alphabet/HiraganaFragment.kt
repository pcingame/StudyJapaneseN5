package com.pc.studyjapanesen5.ui.alphabet

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.Constant.SINGLE_TYPE
import com.pc.studyjapanesen5.databinding.FragmentHiraganaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetSingleBinding
import com.pc.studyjapanesen5.model.entity.AlphabetEntity


class HiraganaFragment :
    BaseFragment<FragmentHiraganaBinding, AlphabetViewModel>(FragmentHiraganaBinding::inflate) {
    override val viewModel: AlphabetViewModel by viewModels()

    private val hiraganaSingleAdapter by lazy {
        SimpleListAdapter<ItemAlphabetSingleBinding, AlphabetEntity>(ItemAlphabetSingleBinding::inflate) { item, _ ->
            tvJpHiraganaSingle.text = item.hiragana
            tvLatinHiraganaSingle.text = item.latin
        }
    }

    override fun setupViews() {
        viewBinding.rcvHiraganaSingle.adapter = hiraganaSingleAdapter
    }

    override fun initData() {
        viewModel.getSingleHiragana(SINGLE_TYPE)
    }

    override fun observeData() {
        viewModel.hiraganaSingle.observe(viewLifecycleOwner) { alphabet ->
            hiraganaSingleAdapter.submitList(alphabet)
        }
    }

}
