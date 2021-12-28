package com.mobillium.simsekfodamy.presentation.category

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCategoryBinding
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment() :
    BaseFragment<CategoryViewModel, FragmentCategoryBinding>(
        R.layout.fragment_category,
        CategoryViewModel::class.java
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeAdapter()

        val linearLayoutManager = LinearLayoutManager(requireContext())

        binding.apply {
            recyclerRecipes.layoutManager = linearLayoutManager
            recyclerRecipes.setHasFixedSize(false)
            recyclerRecipes.adapter = adapter
        }

        viewModel.recipes.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        adapter.onChildItemClicked = {
            findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToRecipeDetailFragment(it.id))
        }

        binding.toolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
