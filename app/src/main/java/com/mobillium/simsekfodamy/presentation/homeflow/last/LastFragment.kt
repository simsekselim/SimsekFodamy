package com.mobillium.simsekfodamy.presentation.homeflow.last

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().theme.applyStyle(R.style.Theme_SimsekFodamy, true)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecipeAdapter()
        adapter.onChildItemClicked = {

            findNavController().navigate(R.id.recipeDetailFragment, bundleOf("recipeId" to it.id))
        }

        val linearLayoutManager = LinearLayoutManager(requireContext())

        binding.apply {
            recyclerRecipes.layoutManager = linearLayoutManager
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
