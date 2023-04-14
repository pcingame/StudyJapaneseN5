package com.pc.studyjapanesen5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.databinding.FragmentShuffleVocabularyBinding
import com.pc.studyjapanesen5.viewmodel.MainViewModel


class ShuffleVocabularyFragment : Fragment() {

    private lateinit var binding: FragmentShuffleVocabularyBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShuffleVocabularyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.result.text = viewModel.listData.toString()
    }
}
