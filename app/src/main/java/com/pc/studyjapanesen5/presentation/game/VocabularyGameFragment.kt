package com.pc.studyjapanesen5.presentation.game

import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentVocabularyGameBinding
import com.pc.studyjapanesen5.databinding.ItemVocabularyGameAnswerBinding
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class VocabularyGameFragment :
    BaseFragment<FragmentVocabularyGameBinding, HomeViewModel>(FragmentVocabularyGameBinding::inflate),
    TextToSpeech.OnInitListener {
    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var textToSpeech: TextToSpeech
    private val args by navArgs<VocabularyGameFragmentArgs>()

    private val vocabularyAnswerAdapter by lazy {
        SimpleListAdapter<ItemVocabularyGameAnswerBinding, VocabularyModel>(
            ItemVocabularyGameAnswerBinding::inflate
        ) { item, _ ->
            tvVocabularyAnswer.text = item.wordMeaning
        }
    }

    override fun setupViews() {
        textToSpeech = TextToSpeech(requireContext(), this)
        viewBinding.rcvAnswer.adapter = vocabularyAnswerAdapter.apply {
            onItemClick = { item, _ ->
                tvVocabularyAnswer.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.background_answer_vocabulary_select,
                    null
                )
            }
        }
    }

    override fun initData() {
        viewModel.getVocabularyGame(args.unit)
    }

    override fun observeData() {
        viewModel.listVocabularyGame.observe(viewLifecycleOwner) { listVocabularyGame ->
            val listAnswer = listVocabularyGame.shuffled().take(4)
            val answer = listAnswer.shuffled().first()
            viewBinding.tvQuestionHira.text = getString(R.string.voca_question_game, answer.newWord)
            if (answer.kanji.isNullOrEmpty()) {
                viewBinding.tvQuestionKanji.visibility = View.GONE
            } else {
                viewBinding.tvQuestionKanji.text =
                    getString(R.string.kanji_question_game, answer.kanji)
            }
            viewBinding.imgSound.click {
                textToSpeech.speak(answer.newWord, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            vocabularyAnswerAdapter.submitList(listAnswer)
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