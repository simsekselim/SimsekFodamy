package com.mobillium.simsekfodamy.presentation.detail


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentRecipeDetailBinding
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.ImageList
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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
        binding.viewModel = viewModel
        binding.toolbar.ivFodamy.isVisible = false
        binding.toolbar.ivLogout.setImageResource(R.drawable.share)
        binding.toolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbar.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.navigate.observe(viewLifecycleOwner,{
            findNavController().navigate(R.id.loginWarningDialog)
        })




        viewModel.recipe.observe(viewLifecycleOwner, { recipe ->
            binding.recipe = recipe
            binding.buttonAddComment.setOnClickListener {
            setRecipeDataToUI(recipe)
                findNavController().navigate(
                    R.id.commentsFragment,
                    bundleOf("recipeCommentId" to recipe.id)
                )
            }
            binding.imageCommentsIcon.setOnClickListener {
                setRecipeDataToUI(recipe)
                findNavController().navigate(
                    R.id.commentsFragment,
                    bundleOf("recipeCommentId" to recipe.id)
                )
            }
            binding.imageRecipe.setOnClickListener {
                findNavController().navigate(R.id.imageSliderFragment, bundleOf("image" to ImageList(viewModel?.recipe?.value?.images!!)))
            }

        })

        viewModel.comment.observe(viewLifecycleOwner, { comment ->
            binding.previewCommit.comment = comment


        })



        viewModel.getRecipeById()
        setListeners()
        viewModel.getFirstComment()
        eventHandler()

    }

    private fun setRecipeDataToUI(recipe : Recipe) {
        binding.apply {
            toolbar.tvFodamy.text = recipe.title
            imageLikeIcon.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    if (recipe.is_liked) R.color.red else R.color.dark
                )
            )
            buttonUserFollow.text =
                if (recipe.user.is_following) getString(R.string.following)
                else getString(R.string.follow_user)

            buttonUserFollow.backgroundTintList =
                if (recipe.user.is_following)
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    ) else null

            buttonUserFollow.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (recipe.user.is_following) R.color.white else R.color.red
                )
            )

        }
    }

    private fun setListeners() {
        binding.apply {
            buttonUserFollow.setOnClickListener {
                viewModel?.onClickFollowButton()
            }
            constraintCommentButton.setOnClickListener {
                viewModel?.onClickAddComment()
            }
            constraintLikeButton.setOnClickListener {
                viewModel?.onClickLike()
            }
            buttonAddComment.setOnClickListener {
                viewModel?.onClickAddComment()
            }
        }
    }

    private fun eventHandler() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.event.collect {
                when (it) {
                    is RecipeDetailViewEvent.FirstComment -> {
                        if (it.comment != null) {
                            setFirstComment(it.comment)
                        } else {
                            setFirstComment(null)
                        }
                    }
                    is RecipeDetailViewEvent.RecipeGot -> {
                        setRecipeDataToUI(it.recipe)
                    }
                }
            }
        }
    }


    private fun setFirstComment(comment: Comment?) {
        if (comment != null) {

            binding.previewCommit.apply {
                textUserName.text = comment.user.username
                textCommentDifference.text = comment.difference
                textCommentBody.text = comment.text
                textUserInfo.text = String.format(
                    getString(R.string.user_info),
                    comment.user.recipe_count,
                    comment.user.followed_count
                )
                if (comment.user.image != null)
                    Picasso.get()
                        .load(comment.user.image.url)
                        .into(imageUser)
                else
                    imageUser.setImageResource(R.drawable.profile)
            }
        } else {
            binding.previewCommit.root.isVisible = false
            binding.textCommentsTitle.text = getString(R.string.no_comments)
        }
    }
}





