package com.pc.studyjapanesen5.view

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
        setOf(R.id.homeFragment, R.id.favoriteFragment, R.id.shuffleVocabularyFragment)

    private val blockPopBackStackFragment =
        setOf(R.id.homeFragment, R.id.favoriteFragment, R.id.shuffleVocabularyFragment)


    override fun setupViews() {
        supportActionBar?.hide()
        initNavigation()
    }

    private fun initNavigation() {
        val appBarConfiguration = AppBarConfiguration(mainFragmentIdSet)
        setupActionBarWithNavController(navController, appBarConfiguration)
        viewBinding.mainBottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isVisible = destination.id in mainFragmentIdSet
            viewBinding.mainBottomNav.isVisible = isVisible
        }
        viewBinding.mainBottomNav.selectedItemId = R.id.homeFragment
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
}
