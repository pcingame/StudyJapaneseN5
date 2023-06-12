package com.pc.studyjapanesen5.presentation.alphabet

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.utils.SoundUtils
import com.pc.studyjapanesen5.databinding.FragmentKatakanaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel

class KatakanaFragment :
    BaseFragment<FragmentKatakanaBinding, AlphabetViewModel>(FragmentKatakanaBinding::inflate) {

    override val viewModel: AlphabetViewModel by viewModels()

    private val katakanaSingleAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.katakana
            tvLatinSingle.text = item.latin
        }
    }

    private val katakanaDakuonAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.katakana
            tvLatinSingle.text = item.latin
        }
    }

    private val katakanaComboAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.katakana
            tvLatinSingle.text = item.latin
        }
    }

    private val katakanaSmallAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            tvJpSingle.text = item.katakana
            tvLatinSingle.text = item.latin
        }
    }

    private val katakanaLongVowelAdapter by lazy {
        SimpleListAdapter<ItemAlphabetBinding, AlphabetModel>(ItemAlphabetBinding::inflate) { item, _ ->
            if (item.katakana == null) {
                this.root.layoutParams.height = 0
            } else {
                tvJpSingle.text = item.katakana
                tvLatinSingle.text = item.latin
            }
        }
    }

    override fun setupViews() {
        viewBinding.layoutKatakanaSingle.tvLearn.text =
            getString(R.string.learn, getString(R.string.katakana))
        viewBinding.layoutKatakanaSingle.rcvSingle.adapter = katakanaSingleAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutKatakanaDakuon.rcvSingle.adapter = katakanaDakuonAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutKatakanaCombo.rcvSingle.adapter = katakanaComboAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutKatakanaSmall.rcvSingle.adapter = katakanaSmallAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutKatakanaLongVowel.rcvSingle.adapter = katakanaLongVowelAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }
    }

    override fun initData() {
        viewModel.getAllJapaneseAlphabet()
    }

    override fun observeData() {
        viewModel.alphabetSingle.observe(viewLifecycleOwner) { alphabet ->
            katakanaSingleAdapter.submitList(alphabet)
        }

        viewModel.alphabetDakuon.observe(viewLifecycleOwner) { alphabet ->
            katakanaDakuonAdapter.submitList(alphabet)
        }

        viewModel.alphabetCombo.observe(viewLifecycleOwner) { alphabet ->
            katakanaComboAdapter.submitList(alphabet)
        }

        viewModel.alphabetSmall.observe(viewLifecycleOwner) { alphabet ->
            katakanaSmallAdapter.submitList(alphabet)
        }

        viewModel.alphabetLongVowel.observe(viewLifecycleOwner) { alphabet ->
            //  val longVowelKatakanaList = alphabet.take(5)
            katakanaLongVowelAdapter.submitList(alphabet)
        }
    }

}
