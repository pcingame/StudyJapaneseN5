package com.pc.studyjapanesen5.presentation.game

import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentVocabularyGameBinding
import com.pc.studyjapanesen5.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class VocabularyGameFragment :
    BaseFragment<FragmentVocabularyGameBinding, HomeViewModel>(FragmentVocabularyGameBinding::inflate),
    TextToSpeech.OnInitListener {
    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var textToSpeech: TextToSpeech
    private val args by navArgs<VocabularyGameFragmentArgs>()


    override fun setupViews() {
        textToSpeech = TextToSpeech(requireContext(), this)

    }

    override fun initData() {
        viewModel.getVocabularyGame(args.unit)
    }

    override fun observeData() {
        viewModel.listVocabularyGame.observe(viewLifecycleOwner) {
            Toast.makeText(context, "${it.size}", Toast.LENGTH_SHORT).show()
            Log.d("TAG", "$it ")
        }
        //textToSpeech.speak(answer.newWord, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.JAPAN)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(requireContext(), "Language not supported", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.shutdown()
    }
}