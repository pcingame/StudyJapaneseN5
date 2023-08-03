package com.pc.studyjapanesen5.presentation.game.alphabet

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.Constant.AlphabetType.HIRAGANA_TYPE
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentAlphabetGameBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetAnswerBinding
import com.pc.studyjapanesen5.di.App
import com.pc.studyjapanesen5.domain.model.AlphabetQuestionModel
import com.pc.studyjapanesen5.presentation.game.GameFragment
import com.pc.studyjapanesen5.presentation.game.GameViewModel
import com.pc.studyjapanesen5.presentation.game.result.ResultActivity
import com.pc.studyjapanesen5.presentation.game.result.ResultFragment.Companion.KEY_GAME_SCORE
import com.pc.studyjapanesen5.presentation.game.result.ResultFragment.Companion.KEY_GAME_TYPE
import com.pc.studyjapanesen5.presentation.game.result.ResultFragment.Companion.KEY_RESULT_ALPHABET
import com.pc.studyjapanesen5.presentation.main.MainActivity
import com.pc.studyjapanesen5.presentation.viewcustom.AlertCommonDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlphabetGameFragment :
    BaseFragment<FragmentAlphabetGameBinding, GameViewModel>(FragmentAlphabetGameBinding::inflate) {
    override val viewModel by viewModel<GameViewModel>()

    private var answer = ""
    private var count = 0
    private var totalMark = 100
    private var gameType = ""

    override fun setupViews() {
        super.setupViews()
        viewBinding.btnCheckAnswer.isEnabled = false
        viewBinding.btnBackGuessGame.click {
            showDialogConfirm()
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showDialogConfirm()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
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
        gameType =
            activity?.intent?.extras?.getString(GameFragment.ALPHABET_GAME) ?: activity?.intent?.extras?.getString(
                KEY_RESULT_ALPHABET
            ) ?: HIRAGANA_TYPE
        viewModel.getAlphabetGameData(gameType)
    }

    override fun observeData() {
        viewModel.listAlphabetQuestionModel.observe(viewLifecycleOwner) { questionData ->
            bindData(questionData)
        }
    }

    private fun bindData(questionData: List<AlphabetQuestionModel>) {
        bindQuestionAndAnswer(count, questionData)
        viewBinding.btnCheckAnswer.click {

            viewBinding.progressBarGame.progress += 1

            if (answer == questionData[count].correctAnswer) {
                count += 1
            } else {
                totalMark -= 5
                count += 1
            }

            if (count < Constant.AlphabetType.NUMBER_OF_QUESTION_ALPHABET) {
                bindQuestionAndAnswer(count, questionData)
                refreshData(true)
            } else {
                val intent = Intent(activity, ResultActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(KEY_GAME_SCORE, totalMark)
                bundle.putString(KEY_GAME_TYPE, gameType)
                intent.putExtras(bundle)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun bindQuestionAndAnswer(count: Int, questionData: List<AlphabetQuestionModel>) {
        with(viewBinding) {
            layoutAnswer1.tvAlphabetAnswer.text = questionData[count].answer1
            layoutAnswer2.tvAlphabetAnswer.text = questionData[count].answer2
            layoutAnswer3.tvAlphabetAnswer.text = questionData[count].answer3
            layoutAnswer4.tvAlphabetAnswer.text = questionData[count].answer4
            tvQuestion.text = questionData[count].question
        }
    }

    private fun onClickAnswer() {
        with(viewBinding) {
            layoutAnswer1.layoutItemAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswer1) }
            layoutAnswer2.layoutItemAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswer2) }
            layoutAnswer3.layoutItemAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswer3) }
            layoutAnswer4.layoutItemAnswer.setOnClickListener { onLayoutClicked(viewBinding.layoutAnswer4) }
        }
    }

    private fun onLayoutClicked(layoutAnswer: ItemAlphabetAnswerBinding) {
        viewBinding.btnCheckAnswer.isEnabled = true
        val stateButtonCheck = viewBinding.btnCheckAnswer.isEnabled
        refreshData(!stateButtonCheck)
        layoutAnswer.layoutItemAnswer.background =
            ContextCompat.getDrawable(App.context, R.drawable.background_answer_alphabet_select)
        val choose = layoutAnswer.tvAlphabetAnswer.text.toString()
        answer = choose
    }

    private fun refreshData(isDisableButton: Boolean) {
        with(viewBinding) {
            layoutAnswer1.layoutItemAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_alphabet_unselect
                )
            layoutAnswer2.layoutItemAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_alphabet_unselect
                )
            layoutAnswer3.layoutItemAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_alphabet_unselect
                )
            layoutAnswer4.layoutItemAnswer.background =
                ContextCompat.getDrawable(
                    App.context,
                    R.drawable.background_answer_alphabet_unselect
                )
            if (isDisableButton) {
                btnCheckAnswer.isEnabled = false
            }
        }
    }
}
