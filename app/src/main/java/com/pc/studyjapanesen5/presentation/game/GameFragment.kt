package com.pc.studyjapanesen5.presentation.game

import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.common.extension.setBackgroundView
import com.pc.studyjapanesen5.databinding.FragmentGameBinding
import com.pc.studyjapanesen5.databinding.ItemUnitBinding
import com.pc.studyjapanesen5.presentation.main.MainViewModel


class GameFragment :
    BaseFragment<FragmentGameBinding, MainViewModel>(FragmentGameBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    private var listGame = listOf<String>()

    private val listGameAdapter by lazy {
        SimpleListAdapter<ItemUnitBinding, String>(ItemUnitBinding::inflate) { item, _ ->
            tvUnit.text = item
        }.apply {
            onItemClick = { _, position ->
                if (position == 0) {
                    navigate(R.id.shuffleFragment)
                }
            }
        }
    }


    override fun setupViews() {
        //viewBinding.rcvListGame.adapter = listGameAdapter
        //listGame = resources.getStringArray(R.array.list_game).toList()
        setupHiraganaGame()
        setupKatakanaGame()
        setupHiraKataGame()
        setupSingleVolGame()
        setupAllVolGame()
    }

    private fun setupHiraganaGame() {
        viewBinding.tvGameHiragana.click {
            navigate(R.id.shuffleFragment)
        }
    }

    private fun setupKatakanaGame() {
        viewBinding.tvGameKatakana.click {

        }
    }

    private fun setupHiraKataGame() {
        viewBinding.tvGameAllHiKa.click {

        }
    }

    private fun setupSingleVolGame() {
        viewBinding.tvGameSingleVol.click {

        }
    }

    private fun setupAllVolGame() {
        viewBinding.tvGameAllVol.click {

        }
    }


    override fun observeData() {
        //listGameAdapter.submitList(listGame)
    }
}
