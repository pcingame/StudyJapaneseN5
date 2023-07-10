package com.pc.studyjapanesen5.presentation.game

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentShuffleBinding
import com.pc.studyjapanesen5.databinding.ItemAlphabetAnswerBinding
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.presentation.alphabet.AlphabetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShuffleFragment :
    BaseFragment<FragmentShuffleBinding, AlphabetViewModel>(FragmentShuffleBinding::inflate) {
    override val viewModel by viewModel<AlphabetViewModel>()
   // private val random = Random()

    private var characterLatin = ""

    private val alphabetAnswerAdapter by lazy {
        SimpleListAdapter<ItemAlphabetAnswerBinding, AlphabetModel>(ItemAlphabetAnswerBinding::inflate) { item, _ ->
            tvAlphabetAnswer.text = item.hiragana
        }
    }

    override fun setupViews() {
        super.setupViews()
        viewBinding.btnBackGuessGame.click {
            //navigate(R.id.gameFragment)
            requireActivity().onBackPressedDispatcher.onBackPressed()

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
            val aA = viewBinding.tvQuestion.text
            val answer = if (characterLatin == aA) "right" else "wrong"
            Toast.makeText(context, answer, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initData() {
        viewModel.getAllJapaneseAlphabet()
    }

    override fun observeData() {
        viewModel.alphabetSingle.observe(viewLifecycleOwner) { list ->
            val listAnswer = list.filter {
                it.latin != null
            }.shuffled().take(4)

            viewBinding.tvQuestion.text = listAnswer.shuffled().first().latin
            alphabetAnswerAdapter.submitList(listAnswer)
        }
    }

}
