package com.pc.studyjapanesen5.presentation.detailVocabulary

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentDetailVocabularyBinding
import com.pc.studyjapanesen5.presentation.home.HomeViewModel


class DetailVocabularyFragment :
    BaseFragment<FragmentDetailVocabularyBinding, HomeViewModel>(FragmentDetailVocabularyBinding::inflate) {
    override val viewModel: HomeViewModel by viewModels()
    private val args by navArgs<DetailVocabularyFragmentArgs>()

    override fun setupViews() {
        super.setupViews()
        Toast.makeText(requireContext(), "${args.unit}", Toast.LENGTH_SHORT).show()
    }

}
