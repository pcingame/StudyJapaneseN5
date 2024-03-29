package com.pc.studyjapanesen5.presentation.game.vocabulary

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentVocabularyGameBinding
import com.pc.studyjapanesen5.databinding.ItemVocabularyGameAnswerBinding
import com.pc.studyjapanesen5.di.App
import com.pc.studyjapanesen5.domain.model.VocabularyQuestionModel
import com.pc.studyjapanesen5.presentation.game.GameViewModel
import com.pc.studyjapanesen5.presentation.game.result.ResultActivity
import com.pc.studyjapanesen5.presentation.game.result.ResultFragment.Companion.KEY_GAME_SCORE
import com.pc.studyjapanesen5.presentation.game.result.ResultFragment.Companion.KEY_GAME_UNIT
import com.pc.studyjapanesen5.presentation.main.MainActivity
import com.pc.studyjapanesen5.presentation.viewcustom.AlertCommonDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class VocabularyGameFragment :
    BaseFragment<FragmentVocabularyGameBinding, GameViewModel>(FragmentVocabularyGameBinding::inflate),
    TextToSpeech.OnInitListener {
    override val viewModel by viewModel<GameViewModel>()
    private lateinit var textToSpeech: TextToSpeech
    private val args by navArgs<VocabularyGameFragmentArgs>()
    private var answer = ""
    private var count = 0
    private var unit = 0
    private var totalScore = 100

    override fun setupViews() {
        textToSpeech = TextToSpeech(requireContext(), this)
        viewBinding.btnCheckAnswer.isEnabled = false
        viewBinding.btnBackGame.click {
            showDialogConfirm()
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showDialogConfirm()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        unit = args.unit
        onClickAnswer()
    }

    private fun showDialogConfirm() {
        AlertCommonDialog(requireContext())
            .setOnClickYesListener {
                val intent = Intent(activity, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putBoolean(MainActivity.FROM_GAME, true)
                intent.putExtras(bundle)
                startActivity(intent)
                activity?.finish()
                it.dismiss()
            }
            .setOnClickNoListener {
                it.dismiss()
            }
            .show()
    }

    override fun initData() {
        viewModel.getVocabularyGame(unit)
    }

    override fun observeData() {
        viewModel.listVocabularyGame.observe(viewLifecycleOwner) { questionData ->
            bindData(questionData)
        }
    }

    private fun bindData(questionData: List<VocabularyQuestionModel>) {
        bindQuestionAndAnswer(count, questionData)
        viewBinding.btnCheckAnswer.click {
            viewBinding.progressBarGame.progress += 1
            if (answer == questionData[count].correctAnswer) {
                count += 1
            } else {
                count += 1
                totalScore -= 10
            }
            if (count < Constant.VocabularyType.NUMBER_OF_QUESTION_ALPHABET) {
                bindQuestionAndAnswer(count, questionData)
                refreshData(true)
            } else {
                viewBinding.progressBarGame.progress = 0
                val intent = Intent(activity, ResultActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(KEY_GAME_SCORE, totalScore)
                bundle.putInt(KEY_GAME_UNIT, unit)
                intent.putExtras(bundle)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun bindQuestionAndAnswer(count: Int, questionData: List<VocabularyQuestionModel>) {
        with(viewBinding) {
            layoutAnswerVol1.tvVocabularyAnswer.text = questionData[count].answer1
            layoutAnswerVol2.tvVocabularyAnswer.text = questionData[count].answer2
            layoutAnswerVol3.tvVocabularyAnswer.text = questionData[count].answer3
            layoutAnswerVol4.tvVocabularyAnswer.text = questionData[count].answer4
            tvQuestionHira.text =
                getString(R.string.voca_question_game, questionData[count].vocabulary)
            val kanjiQuestion = questionData[count].kanji
            if (kanjiQuestion == null) {
                tvQuestionKanji.visibility = View.GONE
            } else {
                tvQuestionKanji.text =
                    getString(R.string.kanji_question_game, questionData[count].kanji)
            }
            imgSound.click {
                textToSpeech.speak(
                    questionData[count].vocabulary,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            }
        }
    }

    private fun onClickAnswer() {
        with(viewBinding) {
            layoutAnswerVol1.tvVocabularyAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswerVol1) }
            layoutAnswerVol2.tvVocabularyAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswerVol2) }
            layoutAnswerVol3.tvVocabularyAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswerVol3) }
            layoutAnswerVol4.tvVocabularyAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswerVol4) }
        }
    }

    private fun onLayoutClicked(layoutAnswerVol: ItemVocabularyGameAnswerBinding) {
        viewBinding.btnCheckAnswer.isEnabled = true
        val stateButtonCheck = viewBinding.btnCheckAnswer.isEnabled
        refreshData(!stateButtonCheck)
        layoutAnswerVol.tvVocabularyAnswer.background =
            ContextCompat.getDrawable(App.context, R.drawable.background_answer_vocabulary_select)
        val choose = layoutAnswerVol.tvVocabularyAnswer.text.toString()
        answer = choose
    }

    private fun refreshData(isDisableButton: Boolean) {
        with(viewBinding) {
            layoutAnswerVol1.tvVocabularyAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_vocabulary_unselect
                )
            layoutAnswerVol2.tvVocabularyAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_vocabulary_unselect
                )
            layoutAnswerVol3.tvVocabularyAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_vocabulary_unselect
                )
            layoutAnswerVol4.tvVocabularyAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_vocabulary_unselect
                )
            if (isDisableButton) {
                btnCheckAnswer.isEnabled = false
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.JAPANESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(requireContext(), "Language not supported", Toast.LENGTH_SHORT)
                    .show()
            } else {
                showInstructionDialog()
            }
        } else {
            Toast.makeText(requireContext(), "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showInstructionDialog() {
        AlertCommonDialog(requireContext())
            .setContentTextSize(Constant.ViewSize.TEXT_SIZE_INSTRUCTION)
            .isGameDialog(false)
            .setContent(R.string.instruction_turn_on_voice)
            .setOnClickYesSystemListener {
                it.dismiss()
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.shutdown()
    }
}