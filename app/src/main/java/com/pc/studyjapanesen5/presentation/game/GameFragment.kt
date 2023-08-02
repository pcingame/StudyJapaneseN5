package com.pc.studyjapanesen5.presentation.game

import android.content.Intent
import android.os.Bundle
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.Constant.AlphabetType.BOTH_HIRA_KATA
import com.pc.studyjapanesen5.common.Constant.AlphabetType.HIRAGANA_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.KATAKANA_TYPE
import com.pc.studyjapanesen5.common.Constant.VocabularyType.ALL_TYPE
import com.pc.studyjapanesen5.common.Constant.VocabularyType.ONE_TYPE
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentGameBinding
import com.pc.studyjapanesen5.presentation.game.alphabet.AlphabetGameActivity
import com.pc.studyjapanesen5.presentation.game.vocabulary.VocabularyGameActivity
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

    private fun setupHiraganaGame() {
        viewBinding.tvGameHiragana.click {
            val bundle = Bundle()
            bundle.putString(ALPHABET_GAME, HIRAGANA_TYPE)
            val intent = Intent(activity, AlphabetGameActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setupKatakanaGame() {
        viewBinding.tvGameKatakana.click {
            val bundle = Bundle()
            bundle.putString(ALPHABET_GAME, KATAKANA_TYPE)
            val intent = Intent(activity, AlphabetGameActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setupHiraKataGame() {
        viewBinding.tvGameAllHiKa.click {
            val bundle = Bundle()
            bundle.putString(ALPHABET_GAME, BOTH_HIRA_KATA)
            val intent = Intent(activity, AlphabetGameActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setupSingleVolGame() {
        viewBinding.tvGameSingleVol.click {
            val bundle = Bundle()
            bundle.putInt(VOCABULARY_GAME, ONE_TYPE)
            val intent = Intent(activity, VocabularyGameActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setupAllVolGame() {
        viewBinding.tvGameAllVol.click {
            val bundle = Bundle()
            bundle.putInt(VOCABULARY_GAME, ALL_TYPE)
            val intent = Intent(activity, VocabularyGameActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    companion object {
        const val VOCABULARY_GAME = "VOCABULARY_GAME"
        const val ALPHABET_GAME = "ALPHABET_GAME"
    }
}
