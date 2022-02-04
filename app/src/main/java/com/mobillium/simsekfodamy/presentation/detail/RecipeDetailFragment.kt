package com.mobillium.simsekfodamy.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.mobillium.domain.model.Comment
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
        binding.toolbar.ivFodamy.isVisible = false
        binding.toolbar.ivLogout.setImageResource(R.drawable.share)

        setFragmentResultListener(REQUEST_UNFOLLOW) { requestKey, bundle ->
            if (bundle.getBoolean(UNFOLLOW, false)) {

                viewModel.unfollowUser()
            }
        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            binding.toolbar.tvFodamy.text = recipe.title
            binding.recipe = recipe
            binding.imageRecipe.setOnClickListener {
                viewModel.toImageSlider()
            }
        }

        viewModel.comment.observe(viewLifecycleOwner) { comment ->
            binding.previewCommit.comment = comment
        }



        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.event.observe(viewLifecycleOwner) {
                when (it) {
                    is RecipeDetailViewEvent.FirstComment -> {
                        if (it.comment != null) {
                            setFirstComment(it.comment)
                        } else {
                            setFirstComment(null)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setFirstComment(comment: Comment?) {
        if (comment == null) {
            binding.previewCommit.root.isVisible = false
            binding.textCommentsTitle.text = getString(R.string.no_comments)
        }
    }
    companion object {
        const val REQUEST_UNFOLLOW = "request_unfollow"
        const val UNFOLLOW = "unfollow"
    }
}
