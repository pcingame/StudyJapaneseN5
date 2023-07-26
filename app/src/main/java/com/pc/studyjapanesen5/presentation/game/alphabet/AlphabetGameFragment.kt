package com.pc.studyjapanesen5.presentation.game.alphabet

import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.dialog.AlertNotice
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentAlphabetGameBinding
import com.pc.studyjapanesen5.presentation.alphabet.AlphabetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlphabetGameFragment :
    BaseFragment<FragmentAlphabetGameBinding, AlphabetViewModel>(FragmentAlphabetGameBinding::inflate) {
    override val viewModel by viewModel<AlphabetViewModel>()
    private val args by navArgs<AlphabetGameFragmentArgs>()

    private var characterLatin = ""
    private var count = 0
    private val alphabetAnswerAdapter by lazy {
        AlphabetGameAdapter(
            requireContext(),
            args.gameType
        )
    }

    override fun setupViews() {

        viewBinding.btnBackGuessGame.click {
            showDialogExitGame()
        }


        viewBinding.rcvAnswer.adapter = alphabetAnswerAdapter


        viewBinding.btnCheckAnswer.click {
//            val aA = viewBinding.tvQuestion.text
//            val answer = if (characterLatin == aA) "right" else "wrong"
//            Toast.makeText(context, answer, Toast.LENGTH_SHORT).show()
//            viewBinding.progressBarGame.progress += progressValue

            navigate(R.id.resultFragment)

        }
    }

    private fun showDialogExitGame() {
        AlertNotice.show(
            context,
            getString(R.string.dialog_exit_game_question),
            null,
            R.string.dialog_exit_game_exit,
            R.string.dialog_exit_game_continue
        ) {
            navigate(R.id.gameFragment)
        }
    }

    override fun initData() {
        viewModel.getAllJapaneseAlphabet()
    }

    override fun observeData() {
        viewModel.allAlphabet.observe(viewLifecycleOwner) { list ->
            viewBinding.tvQuestion.text = list.shuffled().first().latin
            alphabetAnswerAdapter.submitList(list.take(1))
        }
    }

}
