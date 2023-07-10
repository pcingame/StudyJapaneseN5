package com.pc.studyjapanesen5.presentation.game

import android.util.Log
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameFragment :
    BaseFragment<FragmentGameBinding, GameViewModel>(FragmentGameBinding::inflate) {

    override val viewModel by viewModel<GameViewModel>()

    override fun setupViews() {
        setupHiraganaGame()
        setupKatakanaGame()
        setupHiraKataGame()
        setupSingleVolGame()
        setupAllVolGame()
    }

    override fun initData() {
        viewModel.getAlphabet()
    }

    private fun setupHiraganaGame() {
        viewBinding.tvGameHiragana.click {
            navigate(R.id.shuffleFragment)
        }
    }

    private fun setupKatakanaGame() {
        viewBinding.tvGameKatakana.click {
            navigate(R.id.shuffleFragment)
        }
    }

    private fun setupHiraKataGame() {
        viewBinding.tvGameAllHiKa.click {
            navigate(R.id.shuffleFragment)
        }
    }

    private fun setupSingleVolGame() {
        viewBinding.tvGameSingleVol.click {
            navigate(R.id.listUnitVocabularyFragment)
        }
    }

    private fun setupAllVolGame() {
        viewBinding.tvGameAllVol.click {
            navigate(R.id.shuffleFragment)
        }
    }


    override fun observeData() {
        viewModel.hiraganaAlphabet.observe(viewLifecycleOwner) {
            val a = it.size
            Log.d("aaa", "$a ")
        }
        viewModel.katakanaAlphabet.observe(viewLifecycleOwner) {
            val b = it.size
            Log.d("bbb", "$b")
        }
    }
}
