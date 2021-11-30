package com.mobillium.simsekfodamy.presentation.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentHomeBinding
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ),
    RecipeAdapter.OnItemClickListener {
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
        val pagerAdapter = HomePagerAdapter(childFragmentManager, lifecycle)
        binding.pager.adapter = pagerAdapter
        TabLayoutMediator(binding.layout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.editor_choose)
                1 -> getString(R.string.last_add)
                else -> getString(R.string.editor_choose)

            }
        }.attach()



        val adapter = RecipeAdapter(this)

        binding.apply {
            recyclerRecipes.layoutManager = LinearLayoutManager(requireContext())
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


/*

        binding.apply {

            viewModel?.sortFilter?.observe(viewLifecycleOwner) {
                when (it) {
                    HomeViewModel.LAST_ADDED -> {

                    }
                    HomeViewModel.EDITOR_CHOICE -> {

                    }
                }
            }
            buttonEditorFilter.setOnClickListener {

                viewModel?.onClickEditorChoose()
            }

            buttonLastAddedFilter.setOnClickListener {

                viewModel?.onClickLastAdded()
            }
        }

        setRecyclerView()
    }



    private fun setRecyclerView() {
        binding.apply {
            recyclerRecipes.layoutManager = LinearLayoutManager(requireContext())
            recyclerRecipes.setHasFixedSize(false)
        }
        setAdapter()
    }

    private fun setAdapter() {
        val adapter = RecipeAdapter(this)
        binding.recyclerRecipes.adapter = adapter
        adapter.addLoadStateListener { state ->
            binding.apply {
                when (state.source.refresh) {
                    is LoadState.Loading -> {
                        recyclerRecipes.isVisible = false

                    }
                    is LoadState.NotLoading -> {
                        recyclerRecipes.isVisible = true
                        recyclerRecipes.smoothScrollToPosition(0)

                    }
                    is LoadState.Error -> {

                    }
                }
            }
        }
        observeRecipes(adapter)
    }

    private fun observeRecipes(adapter: RecipeAdapter) {
        viewModel.recipes.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }



    override fun onItemClick(recipe: Recipe) {

    }
}

 */


