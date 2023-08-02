package com.pc.studyjapanesen5.presentation.game.result

import com.pc.studyjapanesen5.base.BaseActivity
import com.pc.studyjapanesen5.databinding.ActivityResultBinding

class ResultActivity : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) {
    override fun setupViews() {
        supportActionBar?.hide()
    }
}