package com.pc.studyjapanesen5.presentation.game

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.Constant.AlphabetType.HIRAGANA_TYPE
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentAlphabetGameBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetAnswerBinding
import com.pc.studyjapanesen5.di.App
import com.pc.studyjapanesen5.domain.model.AlphabetQuestionModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlphabetGameFragment :
    BaseFragment<FragmentAlphabetGameBinding, GameViewModel>(FragmentAlphabetGameBinding::inflate) {
    override val viewModel by viewModel<GameViewModel>()

    private val gameType =
        activity?.intent?.extras?.getString(GameFragment.ALPHABET_GAME) ?: HIRAGANA_TYPE

    private var answer = ""
    private var count = 0
    private var totalMark = 0

    override fun setupViews() {
        super.setupViews()
        viewBinding.btnCheckAnswer.isEnabled = false
        viewBinding.btnBackGuessGame.click {
            navigate(R.id.gameFragment)
            //requireActivity().onBackPressedDispatcher.onBackPressed()
            onDestroyView()
        }
        onClickAnswer()
    }

    override fun initData() {
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
            if (answer == questionData[count].correctAnswer) {
                count += 1
                totalMark += 5
                viewBinding.progressBarGame.progress += 1
                if (count < 5) {
                    bindQuestionAndAnswer(count, questionData)
                    refreshData(true)
                } else {
                    navigate(R.id.resultFragmentA)
                }
            } else {
                Toast.makeText(context, "wrong", Toast.LENGTH_SHORT).show()
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
