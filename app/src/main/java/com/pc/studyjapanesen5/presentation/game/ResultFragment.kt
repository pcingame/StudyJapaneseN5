package com.pc.studyjapanesen5.presentation.game

import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentResultBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ResultFragment() :
    BaseFragment<FragmentResultBinding, GameViewModel>(FragmentResultBinding::inflate) {
    override val viewModel by viewModel<GameViewModel>()

    override fun setupViews() {
        viewBinding.btnOKResult.click {
            navigate(R.id.gameFragment)
        }

        viewBinding.btnPlayAgainResult.click {
            navigate(R.id.shuffleFragment)
        }
        //nBackPress(null,false)
    }

}