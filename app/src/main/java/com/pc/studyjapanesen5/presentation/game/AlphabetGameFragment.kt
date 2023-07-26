package com.pc.studyjapanesen5.presentation.game

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentAlphabetGameBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetAnswerBinding
import com.pc.studyjapanesen5.di.App
import com.pc.studyjapanesen5.domain.model.AlphabetQuestionModel
import com.pc.studyjapanesen5.presentation.alphabet.AlphabetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlphabetGameFragment :
    BaseFragment<FragmentAlphabetGameBinding, AlphabetViewModel>(FragmentAlphabetGameBinding::inflate) {
    override val viewModel by viewModel<AlphabetViewModel>()
    private val args by navArgs<AlphabetGameFragmentArgs>()

    private var answer = ""
    private var count = 0

    override fun setupViews() {
        super.setupViews()
        viewBinding.btnBackGuessGame.click {
            navigate(R.id.gameFragment)
            //requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        onClickAnswer()
    }

    override fun initData() {
        when (args.gameType) {
            Constant.AlphabetType.HIRAGANA_TYPE -> viewModel.getAlphabetGameData(Constant.AlphabetType.HIRAGANA_TYPE)
            Constant.AlphabetType.KATAKANA_TYPE -> viewModel.getAlphabetGameData(Constant.AlphabetType.KATAKANA_TYPE)
            else -> viewModel.getAlphabetGameData(Constant.AlphabetType.BOTH_HIRA_KATA)
        }
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
                viewBinding.progressBarGame.progress += 1
                if (count < 5) {
                    bindQuestionAndAnswer(count, questionData)
                    refreshData()
                } else {
                    navigate(R.id.resultFragment)
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
        refreshData()
        layoutAnswer.layoutItemAnswer.background =
            ContextCompat.getDrawable(App.context, R.drawable.background_answer_alphabet_select)
        val choose = layoutAnswer.tvAlphabetAnswer.text.toString()
        answer = choose
        viewBinding.btnCheckAnswer.isEnabled = true
    }

    private fun refreshData() {
        with(viewBinding) {
            layoutAnswer1.layoutItemAnswer.background =
                ContextCompat.getDrawable(App.context, R.drawable.background_answer_alphabet_unselect)
            layoutAnswer2.layoutItemAnswer.background =
                ContextCompat.getDrawable(App.context, R.drawable.background_answer_alphabet_unselect)
            layoutAnswer3.layoutItemAnswer.background =
                ContextCompat.getDrawable(App.context, R.drawable.background_answer_alphabet_unselect)
            layoutAnswer4.layoutItemAnswer.background =
                ContextCompat.getDrawable(App.context, R.drawable.background_answer_alphabet_unselect)
            btnCheckAnswer.isEnabled = false
        }
    }
}
