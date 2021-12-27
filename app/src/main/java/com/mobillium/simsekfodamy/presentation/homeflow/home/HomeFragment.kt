package com.mobillium.simsekfodamy.presentation.homeflow.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentHomeBinding
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.homeflow.adapter.HomePagerAdapter
import com.mobillium.simsekfodamy.presentation.homeflow.editor.EditorViewModel
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import com.mobillium.simsekfodamy.presentation.homeflow.last.LastViewModel
import com.mobillium.simsekfodamy.presentation.loginflow.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(
        R.layout.fragment_home,
        HomeViewModel::class.java
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
        val pagerAdapter = HomePagerAdapter(childFragmentManager, lifecycle)
        binding.pager.adapter = pagerAdapter
        TabLayoutMediator(binding.layout, binding.pager) { tab, position ->
            binding.toolbar.ivBack.isVisible = false
            binding.toolbar.tvBack.isVisible = false


            when (position) {
                0 -> {
                    tab.text = getString(R.string.editor_choose)

                }
                1 -> {
                    tab.text = getText(R.string.last_add)

                }
                else -> {
                    tab.text = getString(R.string.editor_choose)
                }


            }

        }.attach()

//        binding.toolbar.ivLogout.setOnClickListener {
//            viewModel.logout()
//        }
//        viewModel.navigateLogin.observe(viewLifecycleOwner, {
//            val navigateLogin = HomeFragmentDirections.actionHomeFragmentToFragmentLogin()
//            findNavController().navigate(navigateLogin)
//        })

        binding.toolbar.ivLogout.setOnClickListener {
            viewModel.logout()
            Toast.makeText(context,"Çıkış Yapıldı", Toast.LENGTH_LONG).show()
        }
        lifecycleScope.launch {
            viewModel.getToken().asLiveData().observe(viewLifecycleOwner, {
                // Change the Icon
                //Show the message
            })
        }
        viewModel.navigateLogin.observe(viewLifecycleOwner, {
            val navigateLogin = HomeFragmentDirections.actionHomeFragmentToFragmentLogin()
            findNavController().navigate(navigateLogin)
        })

    }


    /*  binding.layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab?.position == 0) {
                viewModel.filterData(EDITOR_CHOICE)
            } else {
                viewModel.filterData(LAST_ADDED)
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

    })

}

 */


}
/*

        val adapter = RecipeAdapter(this)

        val linearLayoutManager = LinearLayoutManager(requireContext())

        binding.apply {
            recyclerRecipes.layoutManager = linearLayoutManager
            recyclerRecipes.setHasFixedSize(false)
            recyclerRecipes.adapter = adapter


        }
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    binding.recyclerRecipes.smoothScrollToPosition(positionStart)

            }

        })
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

 */


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


