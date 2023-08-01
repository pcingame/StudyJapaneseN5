package com.pc.studyjapanesen5.presentation.game

import android.content.Intent
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.common.extension.click
import com.pc.studyjapanesen5.databinding.FragmentResultBinding
import com.pc.studyjapanesen5.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ResultFragment() :
    BaseFragment<FragmentResultBinding, GameViewModel>(FragmentResultBinding::inflate) {
    override val viewModel by viewModel<GameViewModel>()

    override fun setupViews() {
        viewBinding.btnOKResult.click {
           val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

//        viewBinding.btnPlayAgainResult.click {
//            navigate(R.id.shuffleFragment)
//        }
        //nBackPress(null,false)
    }

}