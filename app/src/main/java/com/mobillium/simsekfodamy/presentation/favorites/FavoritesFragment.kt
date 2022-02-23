package com.mobillium.simsekfodamy.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.mobillium.domain.model.Category
import com.mobillium.domain.model.Recipe
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentFavoritesBinding
import com.mobillium.simsekfodamy.presentation.favorites.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment() :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(
        R.layout.fragment_favorites,
        FavoritesViewModel::class.java
    ),CategoryAdapter.OnItemClickListener {

    override val isSharedViewModel: Boolean = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.ivBack.isVisible = false
        binding.toolbar.tvBack.isVisible = false

        val adapter = CategoryAdapter(this)

        binding.apply {
            recyclerCategories.setHasFixedSize(false)
            recyclerCategories.adapter = adapter
        }

        viewModel.category.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        }

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun onSeeAllClick(category: Category) {
        viewModel.seeAll(category.id)
    }

    override fun onRecipeClick(recipe: Recipe) {
        viewModel.onRecipeClick(recipe.id)
    }
}
