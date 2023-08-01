package com.pc.studyjapanesen5.presentation.game

import androidx.navigation.fragment.NavHostFragment
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseActivity
import com.pc.studyjapanesen5.databinding.ActivityAlphabetGameBinding

class AlphabetGameActivity : BaseActivity<ActivityAlphabetGameBinding>(ActivityAlphabetGameBinding::inflate) {

    private val navAlphabetHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navAlphabetHostFragment) as NavHostFragment
    }

    private val navController by lazy {
        navAlphabetHostFragment.navController
    }

    override fun setupViews() {
        supportActionBar?.hide()
    }

}