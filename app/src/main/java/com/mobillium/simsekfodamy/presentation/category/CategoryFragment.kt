package com.mobillium.simsekfodamy.presentation.category


import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCategoryBinding
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment() :
    BaseFragment<CategoryViewModel, FragmentCategoryBinding>(
        R.layout.fragment_category,
        CategoryViewModel::class.java
    ),
    RecipeAdapter.OnItemClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeAdapter(this)

        val linearLayoutManager = LinearLayoutManager(requireContext())



        binding.apply {
            recyclerRecipes.layoutManager = linearLayoutManager
            recyclerRecipes.setHasFixedSize(false)
            recyclerRecipes.adapter = adapter


        }

        viewModel.recipes.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }




    }

    override fun onItemClick(recipe: Recipe) {

    }

}