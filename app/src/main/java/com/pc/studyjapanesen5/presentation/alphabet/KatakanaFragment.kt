package com.pc.studyjapanesen5.presentation.alphabet

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.Constant.AlphabetType.COMBO_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.DAKUON_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.LONG_VOWEL_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.SINGLE_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.SMALL_TYPE
import com.pc.studyjapanesen5.databinding.FragmentKatakanaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import java.util.Locale

class KatakanaFragment :
    BaseFragment<FragmentKatakanaBinding, AlphabetViewModel>(FragmentKatakanaBinding::inflate),
    TextToSpeech.OnInitListener {

    override val viewModel: AlphabetViewModel by viewModels()
    private lateinit var textToSpeech: TextToSpeech


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
        textToSpeech = TextToSpeech(requireContext(), this)
        viewBinding.layoutKatakanaSingle.rcvSingle.adapter = katakanaSingleAdapter.apply {
            onItemClick = { item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaDakuon.rcvSingle.adapter = katakanaDakuonAdapter.apply {
            onItemClick = { item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaCombo.rcvSingle.adapter = katakanaComboAdapter.apply {
            onItemClick = { item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaSmall.rcvSingle.adapter = katakanaSmallAdapter.apply {
            onItemClick = { item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaLongVowel.rcvSingle.adapter = katakanaLongVowelAdapter.apply {
            onItemClick = { item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun initData() {
        viewModel.getAllJapaneseAlphabet()
    }

    override fun observeData() {
        viewModel.alphabetSingle.observe(viewLifecycleOwner) { alphabet ->
            katakanaSingleAdapter.submitList(alphabet)
            if (alphabet.first().type == SINGLE_TYPE) {
                setupTitleSingle()
            }
        }

        viewModel.alphabetDakuon.observe(viewLifecycleOwner) { alphabet ->
            katakanaDakuonAdapter.submitList(alphabet)
            if (alphabet.first().type == DAKUON_TYPE) {
                setupTitleDakuon()
            }
        }

        viewModel.alphabetCombo.observe(viewLifecycleOwner) { alphabet ->
            katakanaComboAdapter.submitList(alphabet)
            if (alphabet.first().type == COMBO_TYPE) {
                setupTitleCombo()
            }
        }

        viewModel.alphabetSmall.observe(viewLifecycleOwner) { alphabet ->
            katakanaSmallAdapter.submitList(alphabet)
            if (alphabet.first().type == SMALL_TYPE) {
                setupTitleSmall()
            }
        }

        viewModel.alphabetLongVowel.observe(viewLifecycleOwner) { alphabet ->
            katakanaLongVowelAdapter.submitList(alphabet)
            if (alphabet.first().type == LONG_VOWEL_TYPE) {
                setupTitleLongVowel()
            }
        }
    }

    private fun setupTitleSingle() {
        viewBinding.layoutKatakanaSingle.layoutTitleAlphabet.layoutTitleAlphabetOrigin.isVisible =
            true
        viewBinding.layoutKatakanaSingle.layoutTitleAlphabet.layoutTitleDivider.isVisible =
            false
        viewBinding.layoutKatakanaSingle.layoutTitleAlphabet.tvDetailType.isVisible = true
        viewBinding.layoutKatakanaSingle.layoutTitleAlphabet.tvDetailType.text =
            getString(R.string.learn, getString(R.string.katakana))
    }

    private fun setupTitleDakuon() {
        viewBinding.layoutKatakanaDakuon.layoutTitleAlphabet.layoutTitleAlphabetOrigin.isVisible =
            true
        viewBinding.layoutKatakanaDakuon.layoutTitleAlphabet.tvTypeAlphabet.text =
            getString(R.string.dakuon)
    }

    private fun setupTitleCombo() {
        viewBinding.layoutKatakanaCombo.layoutTitleAlphabet.layoutTitleAlphabetOrigin.isVisible =
            true
        viewBinding.layoutKatakanaCombo.layoutTitleAlphabet.tvTypeAlphabet.text =
            getString(R.string.combo)
    }

    private fun setupTitleSmall() {
        viewBinding.layoutKatakanaSmall.layoutTitleAlphabet.layoutTitleAlphabetOrigin.isVisible =
            true
        viewBinding.layoutKatakanaSmall.layoutTitleAlphabet.tvTypeAlphabet.text =
            getString(R.string.small)
    }

    private fun setupTitleLongVowel() {
        viewBinding.layoutKatakanaLongVowel.layoutTitleAlphabet.layoutTitleAlphabetOrigin.isVisible =
            true
        viewBinding.layoutKatakanaLongVowel.layoutTitleAlphabet.tvTypeAlphabet.text =
            getString(R.string.long_vowel)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.JAPAN)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(requireContext(), "Language not supported", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.shutdown()
    }

}
