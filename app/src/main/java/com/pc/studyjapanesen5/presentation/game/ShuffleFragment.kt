package com.pc.studyjapanesen5.presentation.game

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentShuffleBinding


class ShuffleFragment :
    BaseFragment<FragmentShuffleBinding, GameViewModel>(FragmentShuffleBinding::inflate) {
    override val viewModel: GameViewModel by viewModels()
}
