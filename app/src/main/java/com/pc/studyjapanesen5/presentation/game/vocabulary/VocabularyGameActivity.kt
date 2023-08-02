package com.pc.studyjapanesen5.presentation.game.vocabulary

import androidx.navigation.fragment.NavHostFragment
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseActivity
import com.pc.studyjapanesen5.databinding.ActivityVocabularyGameBinding

class VocabularyGameActivity :
    BaseActivity<ActivityVocabularyGameBinding>(ActivityVocabularyGameBinding::inflate) {

    private val navVocabularyHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navAlphabetHostFragment) as NavHostFragment
    }

    override fun setupViews() {
        supportActionBar?.hide()
    }
}