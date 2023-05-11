package com.pc.studyjapanesen5.presentation.shuffle

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentGameBinding
import com.pc.studyjapanesen5.presentation.main.MainViewModel


class GameFragment :
    BaseFragment<FragmentGameBinding, MainViewModel>(FragmentGameBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun setupViews() {
        super.setupViews()
    }
}
