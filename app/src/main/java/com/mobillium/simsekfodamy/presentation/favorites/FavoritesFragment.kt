package com.mobillium.simsekfodamy.presentation.favorites


import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCategoryBinding
import com.mobillium.simsekfodamy.databinding.FragmentFavoritesBinding
import com.mobillium.simsekfodamy.model.Category
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.category.CategoryViewModel
import com.mobillium.simsekfodamy.presentation.favorites.adapter.CategoryAdapter
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
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




    }



    override fun onSeeAllClick(category: Category) {

    }

    override fun onRecipeClick(recipe: Recipe) {

    }

}