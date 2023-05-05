package com.pc.studyjapanesen5.ui.alphabet

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentKatakanaBinding
import com.pc.studyjapanesen5.ui.main.MainViewModel

class KatakanaFragment :
    BaseFragment<FragmentKatakanaBinding, MainViewModel>(FragmentKatakanaBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

}
