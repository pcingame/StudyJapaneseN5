package com.pc.studyjapanesen5.presentation.main

import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseActivity
import com.pc.studyjapanesen5.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    private val mainFragmentIdSet =
        setOf(R.id.homeFragment, R.id.alphabetFragment, R.id.gameFragment)

    private val blockPopBackStackFragment =
        setOf(R.id.homeFragment, R.id.alphabetFragment, R.id.gameFragment)

    private var isFromGame = false


    override fun setupViews() {
        supportActionBar?.hide()
        isFromGame = this.intent.extras?.getBoolean(FROM_GAME, false) ?: false
        initNavigation()
    }

    private fun initNavigation() {
        val appBarConfiguration = AppBarConfiguration(mainFragmentIdSet)
        setupActionBarWithNavController(navController, appBarConfiguration)
        viewBinding.mainBottomNav.setupWithNavController(navController)
        viewBinding.mainBottomNav.itemIconTintList = null
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isVisible = destination.id in mainFragmentIdSet
            viewBinding.mainBottomNav.isVisible = isVisible
        }
        viewBinding.mainBottomNav.selectedItemId =
            if (isFromGame) R.id.gameFragment else R.id.homeFragment
        isFromGame = false
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController.currentDestination?.id in blockPopBackStackFragment) return
        super.onBackPressed()
    }

    companion object {
        const val FROM_GAME = "FROM_GAME"
    }
}
