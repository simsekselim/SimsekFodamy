package com.mobillium.simsekfodamy.presentation.homeflow.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ItemRecipeBinding
import com.mobillium.simsekfodamy.model.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter() :
    PagingDataAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RECIPE_COMPARATOR) {

    var onChildItemClicked: ((Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val item = getItem(pos)
                    if (item != null) {
                        onChildItemClicked?.invoke(item)
                    }
                }
            }
        }

        fun bind(recipe: Recipe) {
            binding.apply {
                tvUsername.text = recipe.user.username
                tvDescription.text =
                    String.format(
                        binding.root.context.getString(R.string.user_info),
                        recipe.user.recipe_count,
                        recipe.user.followed_count
                    )

                if (recipe.user.image != null)
                    Picasso.get()
                        .load(recipe.user.image.url)
                        .placeholder(R.drawable.user)
                        .into(ivProfilePicture)

                else
                    ivProfilePicture.setImageResource(R.drawable.profile)

                tvTitle.text = recipe.title
                tvSubtitle.text = recipe.category.name
                recipeInfo.text =
                    String.format(
                        binding.root.context.getString(R.string.recipe_info),
                        recipe.comment_count,
                        recipe.like_count
                    )

                if (recipe.images[0].url != null)
                    Picasso.get()
                        .load(recipe.images[0].url)
                        .into(ivRecipe)

                ivMadal.visibility =
                    if (recipe.is_editor_choice) View.VISIBLE else View.GONE
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(recipe: Recipe)
    }

    companion object {
        private val RECIPE_COMPARATOR = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem == newItem
        }
    }
}
