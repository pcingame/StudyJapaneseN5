package com.pc.studyjapanesen5.presentation.shuffle

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentShuffleVocabularyBinding
import com.pc.studyjapanesen5.presentation.main.MainViewModel


class ShuffleVocabularyFragment :
    BaseFragment<FragmentShuffleVocabularyBinding, MainViewModel>(FragmentShuffleVocabularyBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun setupViews() {
        super.setupViews()
    }
}
