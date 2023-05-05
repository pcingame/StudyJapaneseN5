package com.pc.studyjapanesen5.ui.alphabet

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AlphabetAdapter(
    fragment: Fragment,
    private var listFragment: List<Fragment>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}
