package com.mobillium.simsekfodamy.presentation.comment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCategoryBinding
import com.mobillium.simsekfodamy.databinding.FragmentCommentsBinding
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.category.CategoryViewModel
import com.mobillium.simsekfodamy.presentation.comment.adapter.CommentsAdapter
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment() :
    BaseFragment<CommentsViewModel, FragmentCommentsBinding>(
        R.layout.fragment_comments,
        CommentsViewModel::class.java
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CommentsAdapter()


        val linearLayoutManager = LinearLayoutManager(requireContext())



        binding.apply {
            recycler.layoutManager = linearLayoutManager
            recycler.setHasFixedSize(false)
            recycler.adapter = adapter


        }

        viewModel.comments.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }


    }


}
