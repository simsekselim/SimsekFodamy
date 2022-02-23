package com.mobillium.simsekfodamy.presentation.homeflow.last

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentLastBinding
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastFragment :
    BaseFragment<LastViewModel, FragmentLastBinding>(
        R.layout.fragment_last,
        LastViewModel::class.java
    ) {

    override val isSharedViewModel: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecipeAdapter()
        adapter.onChildItemClicked = {
            viewModel.detail(it.id)
        }

        binding.apply {
            recyclerRecipes.setHasFixedSize(false)
            recyclerRecipes.adapter = adapter
        }

        viewModel.recipes.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        }
    }
}
