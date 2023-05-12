package com.pc.studyjapanesen5.presentation.home

import androidx.fragment.app.viewModels
import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentHomeBinding
import com.pc.studyjapanesen5.databinding.ItemUnitBinding
import com.pc.studyjapanesen5.presentation.detailVocabulary.DetailVocabularyFragmentArgs

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()

    private val unitAdapter by lazy {
        SimpleListAdapter<ItemUnitBinding, Int>(ItemUnitBinding::inflate) { item, _ ->
            tvUnit.text = getString(R.string.unit, item.toString())
        }
    }

    override fun setupViews() {
        viewBinding.rcvUnit.adapter = unitAdapter.apply {
            onItemClick = { _, position ->
                val bundle = DetailVocabularyFragmentArgs(position + 1).toBundle()
                navigate(R.id.detailVocabularyFragment, bundle)
            }
        }

    }

    override fun initData() {
        viewModel.getAllVocabulary()
    }

    override fun observeData() {
        viewModel.groupUnit.observe(viewLifecycleOwner) { unitList ->
            unitAdapter.submitList(unitList)
        }

    }
}
