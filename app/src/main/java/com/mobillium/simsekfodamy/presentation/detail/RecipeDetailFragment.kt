package com.mobillium.simsekfodamy.presentation.detail


import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentRecipeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment() :
    BaseFragment<RecipeDetailViewModel, FragmentRecipeDetailBinding>
        (
        R.layout.fragment_recipe_detail,
        RecipeDetailViewModel::class.java
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this


            viewModel.recipe.observe(viewLifecycleOwner,{ recipe ->
                binding.recipe = recipe
                binding.buttonAddComment.setOnClickListener {

                    findNavController().navigate(R.id.commentsFragment, bundleOf("recipeCommentId" to recipe.id))
                }

            })

        viewModel.comment.observe(viewLifecycleOwner,{ comment ->
            binding.previewCommit.comment = comment



        })



    }
}
