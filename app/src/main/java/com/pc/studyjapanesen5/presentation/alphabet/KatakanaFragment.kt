package com.pc.studyjapanesen5.presentation.alphabet

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentKatakanaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import java.util.Locale

class KatakanaFragment :
    BaseFragment<FragmentKatakanaBinding, AlphabetViewModel>(FragmentKatakanaBinding::inflate), TextToSpeech.OnInitListener {

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
        viewBinding.layoutKatakanaSingle.tvLearn.text =
            getString(R.string.learn, getString(R.string.katakana))
        viewBinding.layoutKatakanaSingle.rcvSingle.adapter = katakanaSingleAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaDakuon.rcvSingle.adapter = katakanaDakuonAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaCombo.rcvSingle.adapter = katakanaComboAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaSmall.rcvSingle.adapter = katakanaSmallAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.katakana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutKatakanaLongVowel.rcvSingle.adapter = katakanaLongVowelAdapter.apply {
            onItemClick = {item, _ ->
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
