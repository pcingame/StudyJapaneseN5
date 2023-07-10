package com.pc.studyjapanesen5.presentation.alphabet

import com.google.android.material.tabs.TabLayoutMediator
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.databinding.FragmentAlphabetBinding
import com.pc.studyjapanesen5.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlphabetFragment :
    BaseFragment<FragmentAlphabetBinding, MainViewModel>(FragmentAlphabetBinding::inflate) {
    override val viewModel by viewModel<MainViewModel>()

    private val tabTitle = listOf(R.string.hiragana, R.string.katakana)

    override fun setupViews() {
        super.setupViews()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        viewBinding.viewpagerAlphabet.isUserInputEnabled = false
        val alphabetAdapter = AlphabetAdapter(
            this, listOf(
                HiraganaFragment(),
                KatakanaFragment()
            )
        )
        viewBinding.viewpagerAlphabet.adapter = alphabetAdapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            viewBinding.tabLayoutAlphabet,
            viewBinding.viewpagerAlphabet
        ) { tab, position ->
            tab.text = getString(tabTitle[position])
        }.attach()
    }
}
