package com.pc.studyjapanesen5.presentation.alphabet

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentHiraganaBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import java.util.Locale


class HiraganaFragment :
    BaseFragment<FragmentHiraganaBinding, AlphabetViewModel>(FragmentHiraganaBinding::inflate),
    TextToSpeech.OnInitListener {
    override val viewModel: AlphabetViewModel by viewModels()
    private lateinit var textToSpeech: TextToSpeech


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
        textToSpeech = TextToSpeech(requireContext(), this)

        viewBinding.layoutHiraganaSingle.tvLearn.text =
            getString(R.string.learn, getString(R.string.hiragana))
        viewBinding.layoutHiraganaSingle.rcvSingle.adapter = hiraganaSingleAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.hiragana, TextToSpeech.QUEUE_FLUSH, null, null)
            }

        }

        viewBinding.layoutHiraganaDakuon.rcvSingle.adapter = hiraganaDakuonAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.hiragana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutHiraganaCombo.rcvSingle.adapter = hiraganaComboAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.hiragana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutHiraganaSmall.rcvSingle.adapter = hiraganaSmallAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.hiragana, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        viewBinding.layoutHiraganaLongVowel.rcvSingle.adapter = hiraganaLongVowelAdapter.apply {
            onItemClick = {item, _ ->
                textToSpeech.speak(item.hiragana, TextToSpeech.QUEUE_FLUSH, null, null)
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
