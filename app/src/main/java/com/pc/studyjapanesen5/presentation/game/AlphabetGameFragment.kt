package com.pc.studyjapanesen5.presentation.game

import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentAlphabetGameBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetAnswerBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.presentation.alphabet.AlphabetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlphabetGameFragment :
    BaseFragment<FragmentAlphabetGameBinding, AlphabetViewModel>(FragmentAlphabetGameBinding::inflate) {
    override val viewModel by viewModel<AlphabetViewModel>()
    private val args by navArgs<AlphabetGameFragmentArgs>()

    private var characterLatin = ""


    private val alphabetAnswerAdapter by lazy {
        SimpleListAdapter<ItemAlphabetAnswerBinding, AlphabetModel>(ItemAlphabetAnswerBinding::inflate) { item, _ ->
            when (args.gameType) {
                Constant.AlphabetType.HIRAGANA_TYPE -> tvAlphabetAnswer.text = item.hiragana
                Constant.AlphabetType.KATAKANA_TYPE -> tvAlphabetAnswer.text = item.katakana
                else -> tvAlphabetAnswer.text = item.katakana
            }
        }
    }

    override fun setupViews() {
        super.setupViews()
        viewBinding.btnBackGuessGame.click {
            navigate(R.id.gameFragment)
            //requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        viewBinding.rcvAnswer.adapter = alphabetAnswerAdapter.apply {
            onItemClick = { item, position ->

                characterLatin = item.latin.toString()
                layoutItemAnswer.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.background_answer_alphabet_select,
                    null
                )
            }
        }


        viewBinding.btnCheckAnswer.click {
//            val aA = viewBinding.tvQuestion.text
//            val answer = if (characterLatin == aA) "right" else "wrong"
//            Toast.makeText(context, answer, Toast.LENGTH_SHORT).show()
//            viewBinding.progressBarGame.progress += progressValue
            navigate(R.id.resultFragment)
        }
    }

    override fun initData() {
        viewModel.getAllJapaneseAlphabet()
    }

    override fun observeData() {
        viewModel.allAlphabet.observe(viewLifecycleOwner) { list ->
            viewBinding.tvQuestion.text = list.shuffled().first().latin
            alphabetAnswerAdapter.submitList(list)
        }
    }

}
