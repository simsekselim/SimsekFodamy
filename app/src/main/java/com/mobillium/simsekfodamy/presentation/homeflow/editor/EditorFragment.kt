package com.mobillium.simsekfodamy.presentation.homeflow.editor

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentEditorBinding
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorFragment :
    BaseFragment<EditorViewModel, FragmentEditorBinding>(
        R.layout.fragment_editor,
        EditorViewModel::class.java
    ),
    RecipeAdapter.OnItemClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeAdapter()
        adapter.onChildItemClicked = {

            viewModel.editor(it.id)
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

    override fun onItemClick(recipe: Recipe) {
    }
}
