package com.pc.studyjapanesen5.view

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentHomeBinding
import com.pc.studyjapanesen5.model.WordEntity
import com.pc.studyjapanesen5.viewmodel.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun setupViews() {
        viewBinding.btnAdd.setOnClickListener {
            val newWord = viewBinding.edtNewWord.text.toString()
            val pronounce = viewBinding.edtPronoun.text.toString()
            val meaning = viewBinding.edtMeaning.text.toString()
            viewModel.addNewWord(WordEntity(0, newWord, pronounce, meaning))
        }

        viewModel.listData.observe(viewLifecycleOwner) {
            viewBinding.list.text = it.toString()
        }
    }
}
