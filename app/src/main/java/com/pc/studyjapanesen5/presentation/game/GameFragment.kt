package com.pc.studyjapanesen5.presentation.game

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentGameBinding
import com.pc.studyjapanesen5.databinding.ItemUnitBinding
import com.pc.studyjapanesen5.presentation.main.MainViewModel


class GameFragment :
    BaseFragment<FragmentGameBinding, MainViewModel>(FragmentGameBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    val listGame = listOf("Shuffle")

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
        viewBinding.rcvListGame.adapter = listGameAdapter
    }

    override fun observeData() {
        listGameAdapter.submitList(listGame)
    }
}
