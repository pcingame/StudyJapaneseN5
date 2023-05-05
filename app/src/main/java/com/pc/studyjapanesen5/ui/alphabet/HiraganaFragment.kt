package com.pc.studyjapanesen5.ui.alphabet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentHiraganaBinding
import com.pc.studyjapanesen5.ui.main.MainViewModel


class HiraganaFragment :
    BaseFragment<FragmentHiraganaBinding, MainViewModel>(FragmentHiraganaBinding::inflate) {
    override val viewModel: MainViewModel by viewModels()

}
