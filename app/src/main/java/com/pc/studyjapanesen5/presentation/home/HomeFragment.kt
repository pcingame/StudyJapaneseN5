package com.pc.studyjapanesen5.presentation.home

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentHomeBinding
import com.pc.studyjapanesen5.presentation.main.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun setupViews() {
    }
}
