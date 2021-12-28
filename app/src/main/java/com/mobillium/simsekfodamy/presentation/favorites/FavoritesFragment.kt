package com.mobillium.simsekfodamy.presentation.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentFavoritesBinding
import com.mobillium.simsekfodamy.model.Category
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.favorites.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment() :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(
        R.layout.fragment_favorites,
        FavoritesViewModel::class.java
    ),
    CategoryAdapter.OnItemClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.ivBack.isVisible = false
        binding.toolbar.tvBack.isVisible = false

        val adapter = CategoryAdapter(this)

        val linearLayoutManager = LinearLayoutManager(requireContext())

        binding.apply {
            recyclerCategories.layoutManager = linearLayoutManager
            recyclerCategories.setHasFixedSize(false)
            recyclerCategories.adapter = adapter
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        }

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
            Toast.makeText(context, "Çıkış Yapıldı", Toast.LENGTH_LONG).show()
        }

        viewModel.navigateLogin.observe(viewLifecycleOwner, {
            val navigateLogin = FavoritesFragmentDirections.actionFavoritesFragmentToFragmentLogin()
            findNavController().navigate(navigateLogin)
        })
    }

    override fun onSeeAllClick(category: Category) {
        findNavController().navigate(R.id.categoryFragment, bundleOf("categoryId" to category.id))
    }

    override fun onRecipeClick(recipe: Recipe) {
        findNavController().navigate(R.id.recipeDetailFragment, bundleOf("recipeId" to recipe.id))
    }
}
