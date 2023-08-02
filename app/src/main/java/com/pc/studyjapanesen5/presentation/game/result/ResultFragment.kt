package com.pc.studyjapanesen5.presentation.game.result

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentResultBinding
import com.pc.studyjapanesen5.presentation.game.GameViewModel
import com.pc.studyjapanesen5.presentation.game.alphabet.AlphabetGameActivity
import com.pc.studyjapanesen5.presentation.game.vocabulary.VocabularyGameActivity
import com.pc.studyjapanesen5.presentation.main.MainActivity
import com.pc.studyjapanesen5.presentation.main.MainActivity.Companion.FROM_GAME
import org.koin.androidx.viewmodel.ext.android.viewModel


class ResultFragment() :
    BaseFragment<FragmentResultBinding, GameViewModel>(FragmentResultBinding::inflate) {
    override val viewModel by viewModel<GameViewModel>()

    override fun setupViews() {
        val gameScore = activity?.intent?.extras?.getInt(KEY_GAME_SCORE) ?: 0
        val gameType = activity?.intent?.extras?.getString(KEY_GAME_TYPE) ?: ""
        val gameUnit = activity?.intent?.extras?.getInt(KEY_GAME_UNIT) ?: -1
        viewBinding.tvMarkResult.text = gameScore.toString()

        viewBinding.btnOKResult.click {
            val intent = Intent(activity, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean(FROM_GAME, true)
            intent.putExtras(bundle)
            startActivity(intent)
            activity?.finish()
        }

        viewBinding.btnPlayAgainResult.click {
            val intent1 = Intent(activity, AlphabetGameActivity::class.java)
            val intent2 = Intent(activity, VocabularyGameActivity::class.java)
            if (gameType.isEmpty()) {
                val bundle = Bundle()
                bundle.putInt(KEY_RESULT_VOCABULARY, gameUnit)
                intent2.putExtras(bundle)
                startActivity(intent2)
            } else {
                val bundle = Bundle()
                bundle.putString(KEY_RESULT_ALPHABET, gameType)
                intent1.putExtras(bundle)
                startActivity(intent1)
            }
            activity?.finish()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun observeData() {

    }

    companion object {
        const val KEY_GAME_SCORE = "KEY_GAME_SCORE"
        const val KEY_GAME_TYPE = "KEY_GAME_TYPE"
        const val KEY_GAME_UNIT = "KEY_GAME_TYPE"
        const val KEY_RESULT_ALPHABET = "KEY_RESULT_ALPHABET"
        const val KEY_RESULT_VOCABULARY = "KEY_RESULT_VOCABULARY"
    }

}