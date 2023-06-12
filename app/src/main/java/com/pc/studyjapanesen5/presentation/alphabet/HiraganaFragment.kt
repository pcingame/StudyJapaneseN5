package com.pc.studyjapanesen5.presentation.alphabet

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.utils.SoundUtils
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
        viewBinding.layoutHiraganaSingle.rcvSingle.adapter = hiraganaSingleAdapter.apply {
            onItemClick = { it, _ ->
                val latin = if (it.latin?.equals("n/m", true) == true) {
                    "nm"
                } else it.latin
                SoundUtils.getFileMp3FromAsset(requireContext(), "$latin.mp3")
            }
        }

        viewBinding.layoutHiraganaDakuon.rcvSingle.adapter = hiraganaDakuonAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutHiraganaCombo.rcvSingle.adapter = hiraganaComboAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutHiraganaSmall.rcvSingle.adapter = hiraganaSmallAdapter.apply {
            onItemClick = { it, _ ->
                SoundUtils.getFileMp3FromAsset(requireContext(), "${it.latin}.mp3")
            }
        }

        viewBinding.layoutHiraganaLongVowel.rcvSingle.adapter = hiraganaLongVowelAdapter.apply {
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

    override fun onDestroyView() {
        super.onDestroyView()
        SoundUtils.stopMp3()
    }

}
