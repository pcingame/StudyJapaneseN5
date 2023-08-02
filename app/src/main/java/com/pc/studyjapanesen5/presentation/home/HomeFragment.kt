package com.pc.studyjapanesen5.presentation.home

import com.pc.studyjapanesen5.R
import com.pc.studyjapanesen5.base.BaseFragment
import com.pc.studyjapanesen5.base.recyclerview.SimpleListAdapter
import com.pc.studyjapanesen5.databinding.FragmentHomeBinding
import com.pc.studyjapanesen5.databinding.ItemUnitPlusBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel by viewModel<HomeViewModel>()

    private val unitAdapter by lazy {
        SimpleListAdapter<ItemUnitPlusBinding, Int>(ItemUnitPlusBinding::inflate) { item, _ ->
            tvUnit.text = getString(R.string.unit_number, item.toString())
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
