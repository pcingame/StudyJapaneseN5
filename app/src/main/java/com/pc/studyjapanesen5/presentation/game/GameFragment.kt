package com.pc.studyjapanesen5.presentation.game

import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.Constant
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
        //viewModel.getAlphabet()
    }

    private fun setupHiraganaGame() {
        viewBinding.tvGameHiragana.click {
            val bundle = AlphabetGameFragmentArgs(Constant.AlphabetType.HIRAGANA_TYPE).toBundle()
            navigate(R.id.shuffleFragment, bundle)
        }
    }

    private fun setupKatakanaGame() {
        viewBinding.tvGameKatakana.click {
            val bundle = AlphabetGameFragmentArgs(Constant.AlphabetType.KATAKANA_TYPE).toBundle()
            navigate(R.id.shuffleFragment, bundle)
        }
    }

    private fun setupHiraKataGame() {
        viewBinding.tvGameAllHiKa.click {
            val bundle = AlphabetGameFragmentArgs(Constant.AlphabetType.BOTH_HIRA_KATA).toBundle()
            navigate(R.id.shuffleFragment, bundle)
        }
    }

    private fun setupSingleVolGame() {
        viewBinding.tvGameSingleVol.click {
            navigate(R.id.listUnitVocabularyFragment)
        }
    }

    private fun setupAllVolGame() {
        viewBinding.tvGameAllVol.click {
            val bundle = VocabularyGameFragmentArgs(Constant.VocabularyType.ALL_TYPE).toBundle()
            navigate(R.id.vocabularyGameFragment, bundle)
        }
    }


    override fun observeData() {

    }
}
