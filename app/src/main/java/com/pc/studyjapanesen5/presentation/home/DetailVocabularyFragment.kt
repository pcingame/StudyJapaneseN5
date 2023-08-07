package com.pc.studyjapanesen5.presentation.home

import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentDetailVocabularyBinding
import com.pc.studyjapanesen5.databinding.ItemVocabularyBinding
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class DetailVocabularyFragment :
    BaseFragment<FragmentDetailVocabularyBinding, HomeViewModel>(FragmentDetailVocabularyBinding::inflate),
    TextToSpeech.OnInitListener {
    override val viewModel by viewModel<HomeViewModel>()
    private val args by navArgs<DetailVocabularyFragmentArgs>()
    private lateinit var textToSpeech: TextToSpeech

    private val detailVocabularyAdapter by lazy {
        SimpleListAdapter<ItemVocabularyBinding, VocabularyModel>(ItemVocabularyBinding::inflate) { item, _ ->
            tvVocabulary.text = item.newWord
            tvKanji.text = item.kanji
            tvWordMeaning.text = item.wordMeaning
            layoutHiraganaSound.click {
                textToSpeech.speak(item.newWord, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun setupViews() {
        textToSpeech = TextToSpeech(requireContext(), this)
        viewBinding.layoutTitle.tvVocabulary.text = getString(R.string.hiragana)
        viewBinding.layoutTitle.tvKanji.text = getString(R.string.kanji)
        viewBinding.layoutTitle.tvWordMeaning.text = getString(R.string.meaning)
        viewBinding.layoutTitle.imgSound.visibility = View.GONE
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

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.JAPANESE)
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
